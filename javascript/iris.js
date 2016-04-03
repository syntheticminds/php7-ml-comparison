function Benchmark()
{

    this.attributes = new Array();
    this.classes = new Array();

    this.run = function() {
        this.load();

        var index = 0;
        var total = 0;
        var endTime = Date.now() + (30 * 1000);

        while (Date.now() < endTime) {
            if (index >= this.attributes.length) {
                index = 0;
            }

            this.classify(index);

            index++;
            total++;
        }

        return total;
    }

    this.load = function() {
        var fs  = require("fs");
        var readLine = (function (line) {
            var row = line.split(',');
            this.attributes.push(row.slice(0, 4))
            this.classes.push(row[4]);
        }).bind(this);
        fs.readFileSync('iris.csv').toString().split('\n').forEach(readLine);
    }

    this.classify = function(index) {

        var closest_neighbour_index = null;
        var closest_neighbour_distance = Number.POSITIVE_INFINITY;

        for (var i = 0; i < this.attributes.length; i++) {
            if (i == index) {
                continue;
            }

            var total = 0;
            for (var j = 0; j < 3; j++) {
                total += Math.pow(this.attributes[index][j] - this.attributes[i][j], 2);
            }
            var distance = Math.sqrt(total);

            if (distance < closest_neighbour_distance) {
                closest_neighbour_index = i;
                closest_neighbour_distance = distance;
            }
        }

        return this.classes[closest_neighbour_index];
    }

}

console.log('NodeJS ' + process.version);

var benchmark = new Benchmark();
var total = benchmark.run();

console.log(total + ' instances classified in 30 seconds (' + (total / 30).toFixed(0) + ' per second)');