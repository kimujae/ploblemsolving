const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const line1 = input[0].split(' ');
let N = line1[0];
let X = line1[1];

let arr = input[1].split(' ');
let ans = '';
for(let num of arr) {
    if(X > +num) ans += num + " ";
}

console.log(ans.toString());