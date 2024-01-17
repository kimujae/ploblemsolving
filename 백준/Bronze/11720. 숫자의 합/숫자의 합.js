const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString().trim().split('\n');

let N = input[0];
let num = input[1];
let ans = 0;
for(let i = 0; i < num.length; i++) {
    ans += Number(num.charAt(i));
}

console.log(ans);