const input = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');

let line1 = input[0].split(' ');
let line2 = input[1].split(' ');

let N = +line1[0];
let M = +line2[1];

let arr = [];
arr.length = N;
arr.fill(-1);
for (let num of line2) {
    arr[+num - 1] = +num;
}


let ans = 0;
for(let num of line2) {
    let idx = arr.indexOf(+num);

    if(idx == 0) arr.shift();
    else if(idx >= arr.length/2) {
        ans += arr.length - idx;
        arr = moveRight(arr.length - idx, arr);
    }else {
        ans += idx;
        arr = moveLeft(idx, arr);
    }
}
console.log(ans);



function moveLeft(num, arr) {
    for(let i =0; i < num; i++) {
        arr.push(arr.shift());
    }
    arr.shift();
    return arr;
}

function moveRight(num, arr) {
    for(let i =0; i < num; i++) {
        arr.unshift(arr.pop());
    }
    arr.shift();
    return arr;
}
