var Player = (function() {
    function Player(name, pos, range, baloons, life, state) {
        this.name         = name;
        this.pos         = pos;
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
    function Peg(pos) {
        this.pos = pos;
        this.sprite     = null;
    }

    /* 
    Other code goes here.
    */
    
    return Peg; 
})();


var Wall = (function() {
    function Wall(pos) {
        this.pos = pos;   
        this.sprite     = null;
    }
    /* 
    Other code goes here.
    */
    return Wall; 
})();

var Balloon = (function() {
    function Balloon(pos) {
        this.pos = pos;   
        this.sprite     = null;
    }
    /* 
    Other code goes here.
    */
    return Balloon; 
})();

var Powerup = (function() {
    function Balloon(pos, type) {
        this.pos = pos;
        this.type = type;
        this.sprite     = null;
    }
    /* 
    Other code goes here.
    */
    return Powerup; 
})();

