import {Deserializable} from './deserializable.model';
import {ProductSubcategory} from './product-subcategory.model';

export class ProductCategory implements Deserializable {
  categoryId: number;
  productCategoryName: string;
  subcategoryList: ProductSubcategory[];

  deserialize(input: any): this {
    console.log(input);
    return Object.assign(this, input);
  }
}
