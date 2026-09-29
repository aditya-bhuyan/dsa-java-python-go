package main

import (
	"fmt"
	"testing"
)

func TestMaxProfit(t *testing.T) {
	solver := &BestTimeToBuyStock{}

	tests := []struct {
		name     string
		prices   []int
		expected int
	}{
		{"Basic case 1", []int{7, 1, 5, 3, 6, 4}, 5},
		{"Basic case 2", []int{7, 6, 4, 3, 1}, 0},
		{"Basic case 3", []int{2, 4, 1}, 2},
		{"Single element", []int{1}, 0},
		{"Two elements", []int{1, 2}, 1},
		{"Ascending prices", []int{1, 2, 3, 4, 5}, 4},
		{"Descending prices", []int{5, 4, 3, 2, 1}, 0},
		{"All same prices", []int{5, 5, 5, 5}, 0},
		{"Min max at ends", []int{1, 5, 0, 4}, 4},
		{"Empty array", []int{}, 0},
		{"Nil array", nil, 0},
		{"Large numbers", []int{2147483647, 1, 2147483647}, 2147483646},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := solver.MaxProfit(tt.prices)
			if result != tt.expected {
				t.Errorf("MaxProfit(%v) = %d, want %d", tt.prices, result, tt.expected)
			}
		})
	}
}

func TestMaxProfitBruteForce(t *testing.T) {
	solver := &BestTimeToBuyStock{}

	tests := []struct {
		name     string
		prices   []int
		expected int
	}{
		{"Basic case 1", []int{7, 1, 5, 3, 6, 4}, 5},
		{"Basic case 2", []int{7, 6, 4, 3, 1}, 0},
		{"Basic case 3", []int{2, 4, 1}, 2},
		{"Ascending prices", []int{1, 2, 3, 4, 5}, 4},
		{"Descending prices", []int{5, 4, 3, 2, 1}, 0},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			result := solver.MaxProfitBruteForce(tt.prices)
			if result != tt.expected {
				t.Errorf("MaxProfitBruteForce(%v) = %d, want %d", tt.prices, result, tt.expected)
			}
		})
	}
}

func TestBruteForceVsGreedy(t *testing.T) {
	solver := &BestTimeToBuyStock{}

	testCases := [][]int{
		{7, 1, 5, 3, 6, 4},
		{7, 6, 4, 3, 1},
		{2, 4, 1},
		{1, 2, 3, 4, 5},
		{5, 4, 3, 2, 1},
	}

	for _, prices := range testCases {
		greedy := solver.MaxProfit(prices)
		bruteForce := solver.MaxProfitBruteForce(prices)
		if greedy != bruteForce {
			t.Errorf("Mismatch for %v: greedy=%d, bruteForce=%d", prices, greedy, bruteForce)
		}
	}
}

func BenchmarkMaxProfit(b *testing.B) {
	solver := &BestTimeToBuyStock{}
	prices := make([]int, 1000)
	for i := 0; i < 1000; i++ {
		prices[i] = (i * 13) % 10000
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		solver.MaxProfit(prices)
	}
}

func BenchmarkMaxProfitBruteForce(b *testing.B) {
	solver := &BestTimeToBuyStock{}
	prices := make([]int, 100) // Smaller size for brute force
	for i := 0; i < 100; i++ {
		prices[i] = (i * 13) % 10000
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		solver.MaxProfitBruteForce(prices)
	}
}

func main() {
	fmt.Println("Run with: go test -v")
}
