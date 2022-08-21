import axios from 'axios';


const baseApiUrl = 'api/commons';

export default class CommonsService {
  public uploadInventoryExcel(file: FormData) {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/inventory/upload`, file, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        })
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
