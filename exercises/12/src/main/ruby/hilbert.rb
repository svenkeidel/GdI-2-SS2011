class Hilbert
	def self.sector_size(n)
		return n**2 / 4
	end

	def self.visit_order(length, x, y)
		n = 2**length

		if(x < 0 || x >= n || y < 0 || y >= n)
			raise ArgumentError, "x, y is not element of [0, #{n-1}]"
		end

		if(length == 1)
			if(x==0 && y==0) then return 1 end
			if(x==0 && y==1) then return 2 end
			if(x==1 && y==1) then return 3 end
			if(x==1 && y==0) then return 4 end
		else
			if(x <  n/2 && y <  n/2) 
				return self.visit_order(length-1, y, x) + 0

			elsif(x <  n/2 && y >= n/2)
				return self.visit_order(length-1, x, y-n/2) + self.sector_size(n) * 1

			elsif(x >= n/2 && y >= n/2)
				return self.visit_order(length-1, x-n/2, y-n/2) + self.sector_size(n) * 2

			elsif(x >= n/2 && y <  n/2)
				return self.visit_order(length-1, n/2-y-1, n-1-x) + self.sector_size(n) * 3
			end
		end
	end

	def self.order_array(length)
		order = []
		(0..2**length-1).each do |y|
				order << []
			(0..2**length-1).each do |x|
				order[y] << self.visit_order(length, x, y)
			end
		end
		return order
	end

	def self.to_s(length)
		order = self.order_array(length)

		(2**length-1).downto(0) do |y|
			(0..2**length-1).each do |x|
				print self.getUnicode(order, x, y)
			end
			print "\n"
		end
	end

	private
	def self.getUnicode(order, x, y)
		n = order.length
		element = order[y][x]
		if(y > 0 && y < n-1 && 
			 neighbor(order[y][x], order[y+1][x]) &&
			 neighbor(order[y][x], order[y-1][x]))
			return "│"

		elsif(x > 0 && x < n-1 && 
			  neighbor(order[y][x], order[y][x+1]) &&
			  neighbor(order[y][x], order[y][x-1]))
			return "─"

		elsif(x < n-1 && y < n-1 && 
			  neighbor(order[y][x], order[y][x+1]) &&
			  neighbor(order[y][x], order[y+1][x]))
			return "└"

		elsif(x < n-1 && y > 0 && 
			  neighbor(order[y][x], order[y][x+1]) &&
			  neighbor(order[y][x], order[y-1][x]))
			return "┌"

		elsif(x > 0 && y > 0 && 
			  neighbor(order[y][x], order[y][x-1]) &&
			  neighbor(order[y][x], order[y-1][x]))
			return "┐"

		elsif(x > 0 && y < n-1 && 
			  neighbor(order[y][x], order[y][x-1]) &&
			  neighbor(order[y][x], order[y+1][x]))
			return "┘"

		elsif((x == 0 || x == n-1) && y == 0 &&
			   neighbor(order[y][x], order[y+1][x]))
			return "╵"

		elsif(x == 0 && y == 0 && 
			   neighbor(order[y][x], order[y][x+1]))
			return "╶"

		elsif(x == n-1 && y == 0 && 
			   neighbor(order[y][x], order[y][x-1]))
			return "╴"

		else
			return "E"
		end
	end

	def self.neighbor(a, b)
		return (a == b - 1 || a == b + 1)
	end

	def self.vertical_bars(length)
		order = self.order_array(length)

		(2**length-1).downto(0) do |y|
			(0..2**length-1).each do |x|
				c = self.getUnicode(order, x, y)
				if(c == '│')
					puts "(#{x}, #{y}) => #{visit_order(length, x, y)}"
				end
			end
		end
	end
end

length = ARGV.shift.to_i
Hilbert.vertical_bars(length)
Hilbert.to_s(length)
