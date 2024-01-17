const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString();

let alph = Array.from({length : 26}, () => -1);
for(let i = 97; i < 123; i++) {
    let alphabet = String.fromCharCode(i);
    // 알파벳 문자를 반환 받는다 a~z
    alph[i-97] = input.indexOf(alphabet);
}

console.log(alph.join(" "));