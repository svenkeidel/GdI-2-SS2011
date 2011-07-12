require 'hilbert'
require 'test/unit'

class TestHilbert < Test::Unit::TestCase

	def test_sector_size
		assert_equal(1,  Hilbert.sector_size(2))
		assert_equal(4,  Hilbert.sector_size(4))
		assert_equal(16, Hilbert.sector_size(8))
		assert_equal(64, Hilbert.sector_size(16))
	end

	def test_wrong_arguments
		assert_raise(ArgumentError) {Hilbert.visit_order(2, -1,  0)}
		assert_raise(ArgumentError) {Hilbert.visit_order(2,  0, -1)}
		assert_raise(ArgumentError) {Hilbert.visit_order(2,  4,  4)}
		assert_nothing_raised(ArgumentError) {Hilbert.visit_order(2,  3,  3)}
		assert_nothing_raised(ArgumentError) {Hilbert.visit_order(1,  0,  0)}
		assert_nothing_raised(ArgumentError) {Hilbert.visit_order(1,  1,  0)}
		assert_nothing_raised(ArgumentError) {Hilbert.visit_order(1,  1,  1)}
		assert_nothing_raised(ArgumentError) {Hilbert.visit_order(1,  0,  1)}
	end

	def test_hilbert_length_1
		assert_equal(1, Hilbert.visit_order(1, 0, 0))
		assert_equal(2, Hilbert.visit_order(1, 0, 1))
		assert_equal(3, Hilbert.visit_order(1, 1, 1))
		assert_equal(4, Hilbert.visit_order(1, 1, 0))
	end

	def test_hilbert_length_2
		assert_equal( 1, Hilbert.visit_order(2, 0, 0))
		assert_equal( 2, Hilbert.visit_order(2, 1, 0))
		assert_equal( 3, Hilbert.visit_order(2, 1, 1))
		assert_equal( 4, Hilbert.visit_order(2, 0, 1))
		assert_equal( 5, Hilbert.visit_order(2, 0, 2))
		assert_equal( 6, Hilbert.visit_order(2, 0, 3))
		assert_equal( 7, Hilbert.visit_order(2, 1, 3))
		assert_equal( 8, Hilbert.visit_order(2, 1, 2))
		assert_equal( 9, Hilbert.visit_order(2, 2, 2))
		assert_equal(10, Hilbert.visit_order(2, 2, 3))
		assert_equal(11, Hilbert.visit_order(2, 3, 3))
		assert_equal(12, Hilbert.visit_order(2, 3, 2))
		assert_equal(13, Hilbert.visit_order(2, 3, 1))
		assert_equal(14, Hilbert.visit_order(2, 2, 1))
		assert_equal(15, Hilbert.visit_order(2, 2, 0))
		assert_equal(16, Hilbert.visit_order(2, 3, 0))
	end

	def test_hilbert_length_3
		numbers = [
			[22, 23, 26, 27, 38, 39, 42, 43],
			[21, 24, 25, 28, 37, 40, 41, 44],
			[20, 19, 30, 29, 36, 35, 46, 45],
			[17, 18, 31, 32, 33, 34, 47, 48],
			[16, 13, 12, 11, 54, 53, 52, 49],
			[15, 14, 9,  10, 55, 56, 51, 50],
			[2,  3,  8,  7,  58, 57, 62, 63],
			[1,  4,  5,  6,  59, 60, 61, 64]]

		(0..7).each do |y|
			(0..7).each do |x|
				assert_equal(numbers[7-y][x], Hilbert.visit_order(3, x, y))
			end
		end			
	end

	def test_neighbor
		assert(true,  Hilbert.neighbor(1, 2))
		assert(true,  Hilbert.neighbor(2, 1))
		assert(true,  Hilbert.neighbor(2, 3))
		assert(true,  Hilbert.neighbor(3, 2))
		assert(true, !Hilbert.neighbor(1, 3))
		assert(true, !Hilbert.neighbor(3, 1))
	end
end
