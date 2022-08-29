import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import axios from 'axios';
import dayjs from 'dayjs';
import { DATE_TIME_FORMAT, DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';
import { IDashboardData } from '@/shared/model/dashboard-data.model';
import { IShopeeOrderPayments } from '@/shared/model/shopee-order-payments.model';
import { ILazadaOrderPayments } from '@/shared/model/lazada-order-payments.model';

const baseApiUrl = 'api/dashboard';
@Component
export default class Dashboard extends Vue {
  public dashboardData: IDashboardData = null;
  public startDate = dayjs().add(-1, 'day').format(DATE_TIME_FORMAT);
  public endDate = dayjs().format(DATE_TIME_FORMAT);
  public site = 'all';
  public isFetching = false;
  public profit = 0;
  public isViewBreakdown = false;

  public mounted(): void {
    this.search();
  }

  public search(): Promise<any> {
    // eslint-disable-next-line prefer-const
    let formData = new FormData();
    // eslint-disable-next-line prefer-const
    let yesterday = dayjs().add(-1, 'day').format(DATE_TIME_FORMAT);
    // START DATE
    if (this.startDate) {
      formData.append('startDate', this.startDate);
    } else {
      formData.append('startDate', yesterday);
    }

    // END DATE
    if (this.endDate) {
      formData.append('endDate', this.endDate);
    } else {
      formData.append('endDate', dayjs().format(DATE_TIME_FORMAT));
    }

    // SITE
    if (this.site) {
      formData.append('site', this.site);
    } else {
      formData.append('site', 'all');
    }

    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/init`, formData)
        .then(res => {
          resolve(res.data);
          this.dashboardData = res.data;
          this.profit = this.dashboardData.profit;
          this.isFetching = false;
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public query() {}

  public updateStartDate(event) {
    if (event.target.value) {
      this.startDate = dayjs(event.target.value).format(DATE_TIME_FORMAT);
    } else {
      this.startDate = null;
    }
  }
  public updateEndDate(event) {
    if (event.target.value) {
      this.endDate = dayjs(event.target.value).format(DATE_TIME_FORMAT);
    } else {
      this.endDate = null;
    }
  }

  public viewBreakdown() {
    console.log(this.dashboardData);
    this.isViewBreakdown = !this.isViewBreakdown;
  }

  public sumAllShopee(payments: IShopeeOrderPayments[]) {
    let sum = 0;
    payments.forEach((item, index) => {
      sum = sum + item.totalReleasedAmount;
    });

    return sum.toFixed(2);
  }

  public sumAllLazada(payments: ILazadaOrderPayments[]) {
    let sum = 0;
    payments.forEach((item, index) => {
      sum = sum + item.amount;
    });

    return sum.toFixed(2);
  }

  public calculateProfit(gross, cost): number {
    const nGross = parseFloat(gross);
    const nCost = parseFloat(cost);
    return nGross - nCost;
  }
}
