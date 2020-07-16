import {Deserializable} from './deserializable.model';

export class ProductCategory implements Deserializable {
  id: number;
  productCategoryName: string;

  deserialize(input: any): this {
    console.log(input);
    return Object.assign(this, input);
  }
}
