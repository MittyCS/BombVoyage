var backPath = "assets/back.jpg";
var playerPath = "assets/player.png"
var pegPath = "assets/player.png"
var wallPath = "assets/player.png"
var balloonPath = "assets/player.png"
var powerupPath = "assets/player.png"

var renderStage = function (var input) {
	var stage = new PIXI.Stage(0x000000);
	var renderer = PIXI.autoDetectRenderer(window.innerWidth, window.innerHeight);
	document.body.appendChild(renderer.view);

	requestAnimFrame( animate );

	var texture = PIXI.Texture.fromImage(backPath);

	var back = new PIXI.Sprite(texture);

	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			if (input[i][j].sprite == null) {
				switch(instanceof input[i][j])
					case Player:
						input[i][j].sprite = new PIXI.Sprite(playerPath);
					case Peg:
						input[i][j].sprite = new PIXI.Sprite(pegPath);
					case Wall:
						input[i][j].sprite = new PIXI.Sprite(wallPath);
					case Balloon:
						input[i][j].sprite = new PIXI.Sprite(balloonPath);
					case Powerup:			
						input[i][j].sprite = new PIXI.Sprite(powerupPath);
			}
		}
	}

	back.width = window.innerWidth;
	back.height = window.innerHeight;

	stage.addChild(back);

	function animate() {

	    requestAnimFrame( animate );

			    

	    renderer.render(stage);
	}

}

