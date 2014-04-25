/**
 * Views for the main html, all functions under the <code>views</code>
 * namespace must strictly return a string of html to display
 *
 * @author Adrian Chmielewski-Anders
 */


if (views === undefined) {
    views = {};
}

views.makeGame = function() {
    var code = utils.randString();
    utils.post("/newGameCode", code);
    return "<div><p>Tell your friends to type in the following code</p>" +
           "<b>" + code + "</b>" +
           "</div>";
};

// todo: make this better
views.joinGame = function() {
    return "<div>"+
           "<form><input type='text' name='code' value='' placeholder='Enter a game code'></form>" +
           "</div>";
};

// todo: make this better and write up the json in the game spec
// games is a json object of games
views.gameList = function(games) {
    var builder = "<ol>";
    for (var prop in games) {
        if (games.hasOwnProperty(prop)) {
            builder += "<li>" + prop + ": " + games[prop] + "</li>";
        }
    }
    builder += "</ol>"
    return builder;
};