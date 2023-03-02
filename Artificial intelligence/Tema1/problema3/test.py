from solution import solution


def test():
    assert solution([1, 0, 2, 0, 3], [1, 2, 0, 3, 1]) == 4
    assert solution([0, 0, 0, 0], [1, 2, 3, 4]) == 0
