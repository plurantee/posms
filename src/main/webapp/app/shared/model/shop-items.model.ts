export interface IShopItems {
  id?: number;
  stock?: number | null;
  price?: number | null;
}

export class ShopItems implements IShopItems {
  constructor(public id?: number, public stock?: number | null, public price?: number | null) {}
}
