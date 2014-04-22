var stage = new PIXI.Stage(0x000000);
 
var renderer = PIXI.autoDetectRenderer(window.innerWidth, window.innerHeight);


document.body.appendChild(renderer.view);

requestAnimFrame( animate );


var texture = PIXI.Texture.fromImage("assets/back.jpg");

var back = new PIXI.Sprite(texture);

back.width = window.innerWidth;
back.height = window.innerHeight;

stage.addChild(back);

function animate() {

    requestAnimFrame( animate );

    back.rotate += 0.1;

    renderer.render(stage);
}