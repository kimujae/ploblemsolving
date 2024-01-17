const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString().trim();

let alph = Array.from({length : 26}, () => -1);
let basic = "a";
for(let i = 0; i < input.length; i++) {
    let idx = input.charCodeAt(i) - basic.charCodeAt(0);

    if(alph[idx] != -1) continue;
    else alph[idx] = i;
}

let ans = '';
for(let val of alph) {
   ans += val+" ";     
}
console.log(ans);