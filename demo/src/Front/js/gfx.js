
function someFunction() {
        console.log("someFunction called");
        // Make an instance of two and place it on the page.
var params = {
  fullscreen: true
};
var elem = document.body;
var two = new Two(params).appendTo(elem);

// Two.js has convenient methods to make shapes and insert them into the scene.
var width = 100;
var height = 100;

var radius = 60;
var x = 0;
var y = -0.5 * (radius + height);
var triangle = two.makePolygon(x, y, radius, 3);

// The object returned has many stylable properties:
triangle.fill = 'brown';
// And accepts all valid CSS color:
triangle.stroke = 'orangered';
triangle.linewidth = 5;

y = 0;

var rect = two.makeRectangle(x, y, width, height);

rect.fill = 'rgb(0, 200, 255)';
rect.opacity = 0.75;
rect.noStroke();

var ship = two.makeGroup([triangle, rect]);
ship.position.set(two.width / 2, two.height / 2);

ship.rotation = 0.1
// Don’t forget to tell two to draw everything to the screen
two.update();
}