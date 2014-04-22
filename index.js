var stage = new PIXI.Stage(0x000000);
 
    // create a renderer instance.
    var renderer = PIXI.autoDetectRenderer(window.innerWidth, window.innerHeight);
 
    // add the renderer view element to the DOM
    document.body.appendChild(renderer.view);
 
    requestAnimFrame( animate );
 
    // create a texture from an image path
    var texture = PIXI.Texture.fromImage("bunny.png");
    // create a new Sprite using the texture
    var bunny = new PIXI.Sprite(texture);
 
    // center the sprites anchor point
    bunny.anchor.x = 0.5;
    bunny.anchor.y = 0.5;


 	bunny.x = window.innerWidth/2;
    bunny.y = window.innerHeight/2;


    stage.addChild(bunny);
 
    function animate() {
 
        requestAnimFrame( animate );
 
        bunny.rotate += 0.1;
 
        // render the stage   
        renderer.render(stage);
    }