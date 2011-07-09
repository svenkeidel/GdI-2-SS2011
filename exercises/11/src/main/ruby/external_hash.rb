def hash(x)
	sum = 0
	x.upcase.each_byte do |c|
		sum += ?Z - c
	end
	sum %= 7
	puts "#{x} => #{sum}"
end

hash("Mallorca")
hash("Sicilia")
hash("Jeju")
hash("Madagaskar")
hash("Malediven")
hash("Jamaika")
hash("Srilanka")
hash("Bahamas")
hash("Hainau")
hash("Tahiti")
hash("Fidschi")
hash("Ibiza")
hash("Kuba")
hash("Island")
hash("Taiwan")
