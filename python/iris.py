import math
import os.path
import sys
import time

class Iris():
    def run(self):
        self.load()

        total = 0
        index = 0
        start_time = time.time()

        while time.time() < start_time + 30:
            if index >= len(self.attributes):
                index = 0

            answer = self.classify(index=index)

            total += 1
            index += 1

        return total

    def load(self):
        self.attributes = []
        self.classes = []

        with open(os.path.dirname(__file__) + '/../iris.csv') as file:
            for line in file:
                line = line.strip().split(",")
                self.attributes.append([float(i) for i in line[:4]])
                self.classes.append(line[4])

    def classify(self, index):
        closest_neighbour_index = 0
        closest_neighbour_distance = float('inf')

        for i, attributes in enumerate(self.attributes):
            if i == index:
                continue

            total = 0;
            for j, value in enumerate(self.attributes[i]):
                total += math.pow(self.attributes[index][j] - self.attributes[i][j], 2)
            distance = math.sqrt(total)

            if distance < closest_neighbour_distance:
                closest_neighbour_index = i
                closest_neighbour_distance = distance

        return self.classes[closest_neighbour_index]

print("Python " + str(sys.version_info.major) + "." + str(sys.version_info.minor))

benchmark = Iris()
total = benchmark.run()

print(format(total, ",d") + ' instances classified in 30 seconds (' + format(int(total / 30), ",d") +  ' per second)')
