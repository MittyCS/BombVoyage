var backPath = "../assets/back.jpg";
var playerPath = "../assets/player.png";
var pegPath = "../assets/player.png";
var wallPath = "../assets/player.png";
var balloonPath = "../assets/player.png";
var powerupPath = "../assets/player.png";

var renderStage = function (input) {
    var stage = new PIXI.Stage(0x000000);
    var renderer = PIXI.autoDetectRenderer(window.innerWidth, window.innerHeight);
    document.body.appendChild(renderer.view);

    requestAnimFrame(animate);

    var texture = PIXI.Texture.fromImage(backPath);

    var back = new PIXI.Sprite(texture);

    stage.addChild(back);
    back.width = window.innerWidth;
    back.height = window.innerHeight;

    for (var i = 0; i < 12; i++) {
        for (var j = 0; j < 12; j++) {
            if (input[i][j].sprite == null) {
                if (input[i][j] instanceof Player) {
                    input[i][j].sprite = new PIXI.Sprite(playerPath);
                }
                else if (input[i][j] instanceof Peg) {
                    input[i][j].sprite = new PIXI.Sprite(pegPath);
                }
                else if (input[i][j] instanceof Wall) {
                    input[i][j].sprite = new PIXI.Sprite(wallPath);
                }
                else if (input[i][j] instanceof Balloon) {
                    input[i][j].sprite = new PIXI.Sprite(balloonPath);
                }
                else if (input[i][j] instanceof Powerup){            
                    input[i][j].sprite = new PIXI.Sprite(powerupPath);
                } else {
                    console.log("WTF is going on?");
                }
            }
            input[i][j].width = window.innerWidth/12;
            input[i][j].height= window.innerHeight/12;
            input[i][j].x = i * window.innerWidth/12;
            input[i][j].y = j * window.innerHeight/12;
            stage.addChild(input[i][j].sprite);
        }
    }

    function animate() {

        requestAnimFrame(animate);
        renderer.render(stage);
    }

}
