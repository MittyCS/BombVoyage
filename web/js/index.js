function randomIntFromInterval(min,max)
{
    return Math.floor(Math.random()*(max-min+1)+min);
}

console.log("ETC");

var arr = new Array(12);

for (var i = 0; i < arr.length; i++) {
    arr[i] = new Array(12);
    for (var j = 0; j < arr[i].length; j++) {
        var x = randomIntFromInterval(0 , 20);
        if (x == 0) {
            arr[i][j] = new Player(5);
        } else if (x == 1 || x > 6) {
            arr[i][j] = new Peg(5);
        } else if (x == 2 || x > 4 && x < 7) {
            arr[i][j] = new Wall(5);
        } else if (x == 3) {
            arr[i][j] = new Powerup(5);
        } else if (x == 4) {
            arr[i][j] = new Balloon(5);
        }
    }
}

renderStage(arr);