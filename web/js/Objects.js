var Player = (function() {

    function Player(name, range, baloons, life, state) {
        this.name     = name;
        this.range    = range;
        this.balloons = baloons;
        this.life     = life;
        this.state    = state;
        this.sprite   = null;
    }

    return Player;

})();

var Peg = (function() {

    function Peg() {
        this.sprite     = null;
    }
    
    return Peg; 
})();

var Wall = (function() {

    function Wall() {
        this.sprite     = null;
    }

    return Wall;
})();

var Balloon = (function() {

    // how effective is this as a constructor?
    function Balloon() {
        this.sprite = null;
    }

    return Balloon; 
})();

var Powerup = (function() {

    function Powerup(type) {
        this.type = type;
        this.sprite     = null;
    }

    return Powerup; 

})();

// why does this exist?
var Empty = (function() {

    function Empty(type) {

    }
    return Empty;
})();



