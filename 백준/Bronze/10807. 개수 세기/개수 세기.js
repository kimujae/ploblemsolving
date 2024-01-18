const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

let N = Number(input[0]);
let arr = input[1].toString().split(' ');
let v  = Number(input[2]);

let ans = 0;
for(let num of arr) {
    if(v === Number(num)) ans++;
}

console.log(ans);
