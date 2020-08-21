import {Deserializable} from './deserializable.model';
import { SafeUrl } from '@angular/platform-browser';

export class TopNewsImage implements Deserializable {
  id: number;
  filePath: string;
  content: string;
  image: SafeUrl;

  deserialize(input: any): this {
    console.log(input);
    return Object.assign(this, input);
  }
}
