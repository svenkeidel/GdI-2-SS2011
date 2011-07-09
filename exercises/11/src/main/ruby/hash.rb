class Hashmap
	def initialize(m)
		@m          = m
		@map        = Array.new(m)
		@collisions = 0
		@fill       = 0
	end

	def store(k)
		for i in 0..@m
			hash = (k + i) % @m
			if (@map[hash] == nil)
				@map[hash] = k
				@fill += 1
				puts " * insertion of key #{k} at hash #{hash}"
				break
			else
				@collisions += 1
				puts " ! collision while insertion of key #{k} at hash #{hash}"
			end
		end
	end

	def printStatus
		puts "Map       : #{@map.inspect}"
		puts "Fill      : #{@fill}"
		puts "Collisions: #{@collisions}"
	end
end

puts "====================================="
h = Hashmap.new(10)
h.store(1)
h.store(3)
h.store(5)
h.store(9)
h.store(11)
h.store(13)
h.store(15)
h.store(19)
h.store(10)
h.printStatus

puts "====================================="
h = Hashmap.new(11)
h.store(1)
h.store(3)
h.store(5)
h.store(9)
h.store(11)
h.store(13)
h.store(15)
h.store(19)
h.store(10)
h.printStatus

puts "====================================="
h = Hashmap.new(10)
h.store(0)
h.store(2)
h.store(4)
h.store(6)
h.store(8)
h.printStatus

puts "====================================="
h = Hashmap.new(11)
h.store(0)
h.store(2)
h.store(4)
h.store(6)
h.store(8)
h.store(10)
h.store(12)
h.store(14)
h.store(16)
h.store(18)
h.printStatus

puts "====================================="
h = Hashmap.new(10)
h.store(0)
h.store(2)
h.store(4)
h.store(6)
h.store(8)
h.store(10)
h.store(12)
h.store(14)
h.store(16)
h.store(18)
h.printStatus
