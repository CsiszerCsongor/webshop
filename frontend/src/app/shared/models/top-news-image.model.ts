import {Deserializable} from './deserializable.model';

export class TopNewsImage implements Deserializable {
  id: number;
  filePath: string;
  content: string;

  deserialize(input: any): this {
    console.log(input);
    return Object.assign(this, input);
  }
}
