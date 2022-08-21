export interface IOrderTracker {
  id?: number;
  orderItemId?: string | null;
  orderType?: string | null;
  skuReference?: string | null;
  status?: string | null;
  site?: string | null;
  barcodeNumber?: string | null;
  courier?: string | null;
  dateUploaded?: Date | null;
  dateReleasedOrCancelled?: Date | null;
}

export class OrderTracker implements IOrderTracker {
  constructor(
    public id?: number,
    public orderItemId?: string | null,
    public orderType?: string | null,
    public skuReference?: string | null,
    public status?: string | null,
    public site?: string | null,
    public barcodeNumber?: string | null,
    public courier?: string | null,
    public dateUploaded?: Date | null,
    public dateReleasedOrCancelled?: Date | null
  ) {}
}
