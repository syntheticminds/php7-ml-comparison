require 'csv'

class Benchmark

  def initialize
    @attributes = []
    @classes = []
  end

  def run
    self.load
    index = 0
    total = 0
    end_time = Time.now.to_f + 30

    while Time.now.to_f < end_time do
        if index >= @attributes.size
            index = 0
        end

        self.classify(index)

        index+=1
        total+=1
    end
    total
  end

  def classify(index)
    closest_neighbour_index = nil
    closest_neighbour_distance = Float::INFINITY

    for i in @attributes.size.times
        if i == index
          next
        end
        total = 0
        for j in 4.times
            total += (@attributes[index][j].to_f - @attributes[i][j].to_f) ** 2
        end
        distance = Math.sqrt(total)

        if distance < closest_neighbour_distance
          closest_neighbour_index = i
          closest_neighbour_distance = distance
        end
    end

    @classes[closest_neighbour_index]
  end

  def load
    CSV.foreach(File.dirname(File.expand_path(__FILE__)) + "/../iris.csv") do |row|
      @attributes << row.slice(0, 4)
      @classes << row[4]
    end
  end

end

puts "Ruby #{RUBY_VERSION}"
total = Benchmark.new.run

puts "#{total} instances classified in 30 seconds (#{total/30} per second)\n"
