//#include <iostream>
//#include <cstdio>
//#include <set>
//#include <map>
//#include <string>
//#include <cctype>
//#include <cstring>
//#include <cmath>
//#include <algorithm>
//#include <vector>
//using namespace std;
//typedef long long ll;
//const int inf = 1e9; 
//const int N = 110000;
// 
//enum {ENTER, EXIT};
//int n, m;
//int req[N], reqType[N];
//int p[N], rang[N], undoElem[N], undoOldVal[N], rangUndo[N], NFundoElem[N], NFundoOldVal[N], cntUndo;
//int nextFree[N], paired[N];
//char s[100];
// 
//int head[N], nxt[N], to[N], cnte;
// 
////бес сжатия путей
//int find(int v) {
//    if (p[v] == v) return v;
//    return find(p[v]);
//}
// 
//void un(int a, int b) {
//    a = find(a);
//    b = find(b);
//    if (rang[a] < rang[b]) swap(a, b);
//    undoElem[cntUndo] = b;
//    undoOldVal[cntUndo] = p[b];
//    p[b] = a;
//    if (rang[a] == rang[b]) rang[a]++;
//    rangUndo[cntUndo] = a;
//}
// 
//void undo() {
//    cntUndo--;
//    p[undoElem[cntUndo]] = undoOldVal[cntUndo];
//    rang[rangUndo[cntUndo]]--;
//    nextFree[NFundoElem[cntUndo]] = NFundoOldVal[cntUndo];
//}
// 
//void add(int v) {
//    int a = find(v);
//    int b = find((nextFree[a] + 1) % n);
//    un(a, b);
//    a = find(a);
//    NFundoElem[cntUndo] = a;
//    NFundoOldVal[cntUndo] = nextFree[a];
//    nextFree[a] = nextFree[b];
//    cntUndo++;
//}
// 
//void sol(int l, int r) {
//    if (r - l == 1) {
//        if (reqType[l] == ENTER) {
//            req[l] = nextFree[find(req[l])];
//            if (head[req[l]]) {
//                int a = to[head[req[l]]];
//                paired[l] = a;
//                paired[a] = l;
//                head[req[l]] = nxt[head[req[l]]];
//            }
//        }
//        return;
//    }
//    int m = (l + r) / 2;
//    int cntOperDSU = 0;
//    for (int i = m; i < r; i++) {
//        if (reqType[i] == EXIT && paired[i] != i && paired[i] < l) {
//            add(req[i]);
//            cntOperDSU++;
//        }
//    }
//    sol(l, m);
//    for (int i = 0; i < cntOperDSU; i++) undo();
//    cntOperDSU = 0;
//    for (int i = l; i < m; i++) {
//        if (reqType[i] == ENTER && paired[i] != i && paired[i] >= r) {
//            add(req[i]);
//            cntOperDSU++;
//        }
//    }
//    sol(m, r);
//    for (int i = 0; i < cntOperDSU; i++) undo();
//}
// 
//int main () {
//#ifndef ONLINE_JUDGE
//    freopen ("parking.in","r",stdin);
//    freopen ("parking.out","w",stdout);
//#endif
//    cin >> n >> m;
//    for (int i = 0; i < m; i++) {
//        scanf("%s%d", s, &req[i]);
//        req[i]--;
//        if (strcmp(s, "exit") == 0) reqType[i] = EXIT;
//        else reqType[i] = ENTER;
//    }
//    for (int i = 0; i < m; i++) {
//        paired[i] = inf;
//        nextFree[i] = i;
//    }
//    for (int i = m - 1; i >= 0; i--) 
//        if (reqType[i] == EXIT) {
//            ++cnte;
//            to[cnte] = i;
//            nxt[cnte] = head[req[i]];
//            head[req[i]] = cnte;
//        }
//    for (int i = 0; i < n; i++) p[i] = i;
//    sol(0, m);
//    for (int i = 0; i < m; i++)
//        if (reqType[i] == ENTER) printf("%d\n", req[i] + 1);
//}