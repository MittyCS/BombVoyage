var backPath = "assets/back.png";
var playerPath = "assets/player.png";
var pegPath = "assets/peg.png";
var wallPath = "assets/wall.png";
var balloonPath = "assets/balloon.png";
var powerupPath = "assets/pwrup.png";
var w;
var h;

var objH;
var objW;

var pX = 0;
var pY = 0;

if (window.innerWidth <= window.innerHeight) {
    w = window.innerWidth;
    h = w;
} else {
    w = window.innerHeight;
    h = w;
}

function randomIntFromInterval(min,max)
{
    return Math.floor(Math.random()*(max-min+1)+min);
}

console.log("ETC");

var xT;
var yT;

var input = new Array(12);

for (var i = 0; i < input.length; i++) {
    input[i] = new Array(12);
    for (var j = 0; j < input[i].length; j++) {
        input[i][j] = new Empty();
    }
}

input[0][0] = new Player(5);

var stage = new PIXI.Stage(0x000000);
var renderer = PIXI.autoDetectRenderer(w, h);
document.body.appendChild(renderer.view);

requestAnimFrame(animate);

var texture = PIXI.Texture.fromImage(backPath);

var back = new PIXI.Sprite(texture);

back.width = w;
back.height = h;
back.x = 0;
back.y = 0;

stage.addChild(back);

document.addEventListener('keydown', function(event) {
     switch (event.keyCode) {
    case 37: // Left
        xT =  input[pY][pX].sprite.x - w/12; 
        break;
    case 38: // Up
        yT =  input[pY][pX].sprite.y - h/12;
        break;
    case 39: // Right
        xT =  input[pY][pX].sprite.x + w/12; 
        break;
    case 40: // Down
        yT =  input[pY][pX].sprite.y + h/12;
        break;
  }
  if (yT < 0) {
        yT = 0;
    } else if (yT > (h - h/12)) {
        yT = h - h/12;
    }

    if (xT < 0) {
        xT = 0;
    } else if (xT > (w - w/12)) {
        xT= w - w/12;
    }
});



for (var i = 0; i < 12; i++) {
    for (var j = 0; j < 12; j++) {
        if (input[i][j].sprite == null) {
            if (input[i][j] instanceof Player) {
                console.log("PLAYA")
                input[i][j].sprite = new PIXI.Sprite(PIXI.Texture.fromImage(playerPath));
            }
            else if (input[i][j] instanceof Peg) {
                input[i][j].sprite = new PIXI.Sprite(PIXI.Texture.fromImage(pegPath));
            }
            else if (input[i][j] instanceof Wall) {
                input[i][j].sprite = new PIXI.Sprite(PIXI.Texture.fromImage(wallPath));
            }
            else if (input[i][j] instanceof Balloon) {
                input[i][j].sprite = new PIXI.Sprite(PIXI.Texture.fromImage(balloonPath));
            }
            else if (input[i][j] instanceof Powerup){            
                input[i][j].sprite = new PIXI.Sprite(PIXI.Texture.fromImage(powerupPath));
            } else {
                console.log("WTF is going on?");
            }
        }
        input[i][j].sprite.width = w/12;
        input[i][j].sprite.height= h/12;
        input[i][j].sprite.x = i * w/12;
        input[i][j].sprite.y = j * h/12;


        stage.addChild(input[i][j].sprite);
    }
}

var inMvX = false;
var dirX = 0;

var inMvY = false;
var dirY = 0;

function animate() {

    if (input[pY][pX].sprite.x < xT) {
        if (inMvX == true && dirX == 1) {
            inMvX = false;
            input[pY][pX].sprite.x = xT;
        } else {
            input[pY][pX].sprite.x += 4;
            inMvX = true;
            dirX == 0;
        }
    } else if (input[pY][pX].sprite.x > xT) {
        if (inMvX == true && dirX == 0) {
            inMvX = false;
            input[pY][pX].sprite.x = xT;
        } else {
            input[pY][pX].sprite.x -= 4;
            inMvX = true;
            dirX == 1;
        }
    }

    if (input[pY][pX].sprite.y < yT) {
        if (inMvY == true && dirY == 1) {
            inMvY = false;
            input[pY][pX].sprite.y = yT;
        } else {
            input[pY][pX].sprite.y += 4;
            inMvY = true;
            dirY == 0;
        }
    } else if (input[pY][pX].sprite.y > yT) {
        if (inMvY == true && dirY == 0) {
            inMvY = false;
            input[pY][pX].sprite.y = yT;
        } else {
            input[pY][pX].sprite.y -= 4;
            inMvY = true;
            dirY == 1;
        }
    }

    if (input[pY][pX].sprite.y < 0) {
        input[pY][pX].sprite.y = 0;
    } else if (input[pY][pX].sprite.y > (h - h/12)) {
        input[pY][pX].sprite.y = h - h/12;
    }

    if (input[pY][pX].sprite.x < 0) {
        input[pY][pX].sprite.x = 0;
    } else if (input[pY][pX].sprite.x > (w - w/12)) {
        input[pY][pX].sprite.x= w - w/12;
    }


    requestAnimFrame(animate);
    renderer.render(stage);
}
