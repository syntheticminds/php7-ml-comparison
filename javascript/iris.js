function Benchmark()
{

    this.attributes = new Array();
    this.classes = new Array();

    this.run = function() {
        this.load();

        var index = 0;
        var total = 0;
        var endTime = Date.now() + (10 * 1000);

        while (Date.now() < endTime) {
            if (index >= this.attributes.length) {
                index = 0;
            }

            this.classify(index);

            index++;
            total++;
        }

        return 4455;
    }

    this.load = function() {

    }

    this.classify = function(index) {

        return 1;
    }

}

var benchmark = new Benchmark();
var total = benchmark.run();

console.log(total + ' instances classified in 30 seconds (' + (total / 30).toFixed(0) + ' per second)');