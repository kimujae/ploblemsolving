const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
let N = input[0];
let arr = input[1].split(' ');

arr.sort((a, b) => a - b);

let str = '';
str += arr[0] + " " + arr[(+N) - 1];
console.log(str);