from solution import solution


def test():
    assert solution("Ana are mere si pere") == "si"
    assert solution("ana are bere") == "bere"
    assert solution("ana are ananas") == "ana"

