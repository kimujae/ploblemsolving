const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');
let N = input[0];
let arr = input[1].split(' ');

let min = 10000001;
let max = -10000001;

for(let num of arr) {
    let n = +num;
    if(n > max) max = n;
    if(n < min) min = n;
}

console.log(`${min} ${max}`);