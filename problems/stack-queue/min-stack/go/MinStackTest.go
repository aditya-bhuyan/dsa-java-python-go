package main

import "testing"

func TestMinStack(t *testing.T) {

	t.Run("Basic operations", func(t *testing.T) {
		ms := NewMinStack()
		ms.Push(-2)
		ms.Push(0)
		ms.Push(-3)

		if ms.GetMin() != -3 {
			t.Fatalf("expected getMin=-3, got %d", ms.GetMin())
		}

		ms.Pop()

		if ms.Top() != 0 {
			t.Fatalf("expected top=0, got %d", ms.Top())
		}

		if ms.GetMin() != -2 {
			t.Fatalf("expected getMin=-2, got %d", ms.GetMin())
		}
	})

	t.Run("Push duplicate minimums", func(t *testing.T) {
		ms := NewMinStack()
		ms.Push(1)
		ms.Push(1)
		ms.Pop()
		if ms.GetMin() != 1 {
			t.Fatalf("expected getMin=1 after popping duplicate, got %d", ms.GetMin())
		}
	})

	t.Run("Min updates correctly on pop", func(t *testing.T) {
		ms := NewMinStack()
		ms.Push(5)
		ms.Push(3)
		ms.Push(7)
		if ms.GetMin() != 3 {
			t.Fatalf("expected 3, got %d", ms.GetMin())
		}
		ms.Pop() // remove 7
		if ms.GetMin() != 3 {
			t.Fatalf("expected 3, got %d", ms.GetMin())
		}
		ms.Pop() // remove 3
		if ms.GetMin() != 5 {
			t.Fatalf("expected 5, got %d", ms.GetMin())
		}
	})

	t.Run("Single element", func(t *testing.T) {
		ms := NewMinStack()
		ms.Push(42)
		if ms.Top() != 42 {
			t.Fatalf("expected top=42, got %d", ms.Top())
		}
		if ms.GetMin() != 42 {
			t.Fatalf("expected getMin=42, got %d", ms.GetMin())
		}
	})

	t.Run("Increasing values", func(t *testing.T) {
		ms := NewMinStack()
		ms.Push(1)
		ms.Push(2)
		ms.Push(3)
		if ms.GetMin() != 1 {
			t.Fatalf("expected 1, got %d", ms.GetMin())
		}
	})
}
