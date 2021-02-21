import {utils} from 'frog-js'

export default function timestampFilter(value) {
  return utils.format.fullTimestamp(value);
}
