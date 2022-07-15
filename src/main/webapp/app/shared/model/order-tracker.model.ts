export interface IOrderTracker {
  id?: number;
  orderItemId?: string | null;
  orderType?: string | null;
  skuReference?: string | null;
  status?: string | null;
  site?: string | null;
}

export class OrderTracker implements IOrderTracker {
  constructor(
    public id?: number,
    public orderItemId?: string | null,
    public orderType?: string | null,
    public skuReference?: string | null,
    public status?: string | null,
    public site?: string | null
  ) {}
}
