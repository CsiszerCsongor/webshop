import {Deserializable} from './deserializable.model';

export class ProductSubcategory implements Deserializable {
  id: number;
  name: string;

  deserialize(input: any): this {
    console.log(input);
    return Object.assign(this, input);
  }
}
