
class Sorter:
    def _identitate(self,x):
        return x
    
    def _nrgare(self,x):
        return not x

    def sort(self,l,*,key=None, reversed=False, cmp=None):
        pass
    
    

class BubleSort(Sorter):
    
    def comp(self,key,a,b):
        if key(a)==key(b):
            return 1
        return 0
        
    
    def sort(self,l,*,key=None, reversed=None, cmp=None):
        for j in range(1,len(l)):
            for i in range(len(l)-j):
                if reversed==True:
                    if self.comp(key,l[i+1],l[i]):
                        if cmp(l[i+1])>cmp(l[i]):
                            l[i], l[i + 1] = l[i + 1], l[i]
                    elif key(l[i+1])>key(l[i]):
                        l[i], l[i + 1] = l[i + 1], l[i]
                else:
                    if self.comp(key,l[i+1],l[i]):
                        if cmp(l[i+1])<cmp(l[i]):
                            l[i], l[i + 1] = l[i + 1], l[i]
                    elif key(l[i+1])<key(l[i]):
                        l[i], l[i + 1] = l[i + 1], l[i]

class ShellSort(Sorter):
    
    def sort(self,l,*,key=None, reversed=None, cmp=None):
        n = len(l)
        gap = n//2
        while gap > 0:
            for i in range(gap,n):
                temp = key(l[i])
                j = i
                while  j >= gap and key(l[j-gap]) >temp:
                    l[j] = l[j-gap]
                    j -= gap
                l[j] = l[i]
            gap //= 2