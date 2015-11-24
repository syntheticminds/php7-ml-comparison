<?php

class Benchmark
{
    protected $attributes;
    protected $classes;

    public function run()
    {
        $this->load();

        $index = 0;
        $total = 0;
        $start_time = microtime(true);

        while (microtime(true) < $start_time + 30) {
            if ($index >= count($this->attributes)) {
                $index = 0;
            }

            $answer = $this->classify($index);

            $index++;
            $total++;
        }

        return $total;
    }

    protected function load()
    {
        if (($file = fopen(dirname(__FILE__) . '/../iris.csv', "r")) !== false) {
            while (($data = fgetcsv($file, 1000, ",")) !== false) {
                $this->attributes[] = array_slice($data, 0, 4);
                $this->classes[] = $data[4];
            }
            fclose($file);
        }
    }

    protected function classify($index)
    {
        $closest_neighbour_index = null;
        $closest_neighbour_distance = INF;

        for ($i = 0; $i < count($this->attributes); $i++) {
            if ($i == $index) {
                continue;
            }

            $total = 0;
            for ($j = 0; $j < 3; $j++) {
                $total += pow($this->attributes[$index][$j] - $this->attributes[$i][$j], 2);
            }
            $distance = sqrt($total);

            if ($distance < $closest_neighbour_distance) {
                $closest_neighbour_index = $i;
                $closest_neighbour_distance = $distance;
            }
        }

        return $this->classes[$closest_neighbour_index];
    }
}

if (defined('HHVM_VERSION')) {
    echo 'HHVM' . PHP_EOL;
} else {
    echo 'PHP ' . PHP_MAJOR_VERSION . '.' . PHP_MINOR_VERSION . PHP_EOL;
}

$benchmark = new Benchmark;
$total = $benchmark->run();

echo number_format($total) . ' instances classified in 30 seconds (' . number_format($total / 30) . ' per second)' . PHP_EOL;
