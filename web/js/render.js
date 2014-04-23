var backPath = "assets/back.png";
var playerPath = "assets/player.png";
var pegPath = "assets/peg.png";
var wallPath = "assets/wall.png";
var balloonPath = "assets/balloon.png";
var powerupPath = "assets/pwrup.png";
var w;
var h;

if (window.innerWidth <= window.innerHeight) {
    w = window.innerWidth;
    h = w;
} else {
    w = window.innerHeight;
    h = w;
}

var renderStage = function (input) {
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

    function animate() {

        for 

        requestAnimFrame(animate);
        renderer.render(stage);
    }

};

// var stage = new PIXI.Stage(0x66FF99);
 
//     // create a renderer instance.
//     var renderer = PIXI.autoDetectRenderer(innerWidth, innerHeight);
 
//     // add the renderer view element to the DOM
//     document.body.appendChild(renderer.view);
 
//     requestAnimFrame( animate );
 
//     // create a texture from an image path
//     var texture = PIXI.Texture.fromImage("assets/back.png");
//     // create a new Sprite using the texture
//     var bunny = new PIXI.Sprite(texture);
 
//     // center the sprites anchor point
//     bunny.anchor.x = 0.5;
//     bunny.anchor.y = 0.5;
 
//     // move the sprite t the center of the screen
//     bunny.position.x = 200;
//     bunny.position.y = 150;
 
//     stage.addChild(bunny);
 
//     function animate() {
 
//         requestAnimFrame( animate );
 
//         // render the stage   
//         renderer.render(stage);
//     }