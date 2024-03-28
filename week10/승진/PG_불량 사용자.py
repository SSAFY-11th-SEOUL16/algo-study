def is_check(criterion, target):
    import re
    if len(criterion) != len(target):
        return False
    p = re.compile(criterion.replace("*", "."))
    if p.match(target):
        return True
    else:
        return False

def check_ids(user_id_list, banned_id_list):
    for i in range(len(banned_id_list)):
        if not is_check(banned_id_list[i], user_id_list[i]):
            return False
    return True

def solution(user_id, banned_id):
    from itertools import permutations
    cache = set()
    test_cases = permutations(user_id, len(banned_id))
    for test_case in test_cases:
        if check_ids(test_case, banned_id):
            cache.add(tuple(sorted(test_case)))
    return len(cache)