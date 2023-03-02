from solution import solution

def test():
    assert solution("ana are ana are mere rosii ana") == ["mere" , "rosii"]
    assert solution("ana are ana are mere mere si si doua rosii ana") == ["doua" , "rosii"]