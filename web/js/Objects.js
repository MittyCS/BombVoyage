var Player = (function() {
    function Player(name, range, baloons, life, state) {
        this.name         = name;
        this.range         = range;
        this.balloons    = baloons;
        this.life         = life;
        this.state         = state;
        this.sprite     = null;

    }

    /* 
    Other code goes here.
    */
    
    return Player; 
})();

var Peg = (function() {
    function Peg() {
        this.sprite     = null;
    }

    /* 
    Other code goes here.
    */
    
    return Peg; 
})();


var Wall = (function() {
    function Wall() {
        this.sprite     = null;
    }
    /* 
    Other code goes here.
    */
    return Wall; 
})();

var Balloon = (function() {
    function Balloon() {
        this.sprite     = null;
    }
    /* 
    Other code goes here.
    */
    return Balloon; 
})();

var Powerup = (function() {
    function Powerup(type) {
        this.type = type;
        this.sprite     = null;
    }
    /* 
    Other code goes here.
    */
    return Powerup; 
})();

