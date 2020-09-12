The initial commit works.
The second commit doesn't.

The output should be similar to:
```
18> ABC
18> $123
```

but the broken version outputs only:

```
3> ABC
```

The only change is switching the order of the processors on the main stream (lines `19` & `20` in `App.java`)
