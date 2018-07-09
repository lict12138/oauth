!function (a, b, c) {
    function d(a, b) {
        return typeof a === b
    }

    function e() {
        var a, b, c, e, f, g, h;
        for (var i in p)if (p.hasOwnProperty(i)) {
            if (a = [], b = p[i], b.name && (a.push(b.name.toLowerCase()), b.options && b.options.aliases && b.options.aliases.length))for (c = 0; c < b.options.aliases.length; c++)a.push(b.options.aliases[c].toLowerCase());
            for (e = d(b.fn, "function") ? b.fn() : b.fn, f = 0; f < a.length; f++)g = a[f], h = g.split("."), 1 === h.length ? r[h[0]] = e : (!r[h[0]] || r[h[0]]instanceof Boolean || (r[h[0]] = new Boolean(r[h[0]])), r[h[0]][h[1]] = e), t.push((e ? "" : "no-") + h.join("-"))
        }
    }

    function f(a) {
        var b = u.className, c = r._config.classPrefix || "";
        if (v && (b = b.baseVal), r._config.enableJSClass) {
            var d = new RegExp("(^|\\s)" + c + "no-js(\\s|$)");
            b = b.replace(d, "$1" + c + "js$2")
        }
        r._config.enableClasses && (b += " " + c + a.join(" " + c), v ? u.className.baseVal = b : u.className = b)
    }

    function g(a, b) {
        if ("object" == typeof a)for (var c in a)s(a, c) && g(c, a[c]); else {
            a = a.toLowerCase();
            var d = a.split("."), e = r[d[0]];
            if (2 == d.length && (e = e[d[1]]), "undefined" != typeof e)return r;
            b = "function" == typeof b ? b() : b, 1 == d.length ? r[d[0]] = b : (!r[d[0]] || r[d[0]]instanceof Boolean || (r[d[0]] = new Boolean(r[d[0]])), r[d[0]][d[1]] = b), f([(b && 0 != b ? "" : "no-") + d.join("-")]), r._trigger(a, b)
        }
        return r
    }

    function h(a, b) {
        return !!~("" + a).indexOf(b)
    }

    function i() {
        return "function" != typeof b.createElement ? b.createElement(arguments[0]) : v ? b.createElementNS.call(b, "http://www.w3.org/2000/svg", arguments[0]) : b.createElement.apply(b, arguments)
    }

    function j() {
        var a = b.body;
        return a || (a = i(v ? "svg" : "body"), a.fake = !0), a
    }

    function k(a, c, d, e) {
        var f, g, h, k, l = "modernizr", m = i("div"), n = j();
        if (parseInt(d, 10))for (; d--;)h = i("div"), h.id = e ? e[d] : l + (d + 1), m.appendChild(h);
        return f = i("style"), f.type = "text/css", f.id = "s" + l, (n.fake ? n : m).appendChild(f), n.appendChild(m), f.styleSheet ? f.styleSheet.cssText = a : f.appendChild(b.createTextNode(a)), m.id = l, n.fake && (n.style.background = "", n.style.overflow = "hidden", k = u.style.overflow, u.style.overflow = "hidden", u.appendChild(n)), g = c(m, a), n.fake ? (n.parentNode.removeChild(n), u.style.overflow = k, u.offsetHeight) : m.parentNode.removeChild(m), !!g
    }

    function l(a) {
        return a.replace(/([A-Z])/g, function (a, b) {
            return "-" + b.toLowerCase()
        }).replace(/^ms-/, "-ms-")
    }

    function m(b, d) {
        var e = b.length;
        if ("CSS"in a && "supports"in a.CSS) {
            for (; e--;)if (a.CSS.supports(l(b[e]), d))return !0;
            return !1
        }
        if ("CSSSupportsRule"in a) {
            for (var f = []; e--;)f.push("(" + l(b[e]) + ":" + d + ")");
            return f = f.join(" or "), k("@supports (" + f + ") { #modernizr { position: absolute; } }", function (a) {
                return "absolute" == getComputedStyle(a, null).position
            })
        }
        return c
    }

    function n(a) {
        return a.replace(/([a-z])-([a-z])/g, function (a, b, c) {
            return b + c.toUpperCase()
        }).replace(/^-/, "")
    }

    function o(a, b, e, f) {
        function g() {
            k && (delete x.style, delete x.modElem)
        }

        if (f = d(f, "undefined") ? !1 : f, !d(e, "undefined")) {
            var j = m(a, e);
            if (!d(j, "undefined"))return j
        }
        for (var k, l, o, p, q, r = ["modernizr", "tspan"]; !x.style;)k = !0, x.modElem = i(r.shift()), x.style = x.modElem.style;
        for (o = a.length, l = 0; o > l; l++)if (p = a[l], q = x.style[p], h(p, "-") && (p = n(p)), x.style[p] !== c) {
            if (f || d(e, "undefined"))return g(), "pfx" == b ? p : !0;
            try {
                x.style[p] = e
            } catch (s) {
            }
            if (x.style[p] != q)return g(), "pfx" == b ? p : !0
        }
        return g(), !1
    }

    var p = [], q = {
        _version: "3.3.1",
        _config: {classPrefix: "", enableClasses: !0, enableJSClass: !0, usePrefixes: !0},
        _q: [],
        on: function (a, b) {
            var c = this;
            setTimeout(function () {
                b(c[a])
            }, 0)
        },
        addTest: function (a, b, c) {
            p.push({name: a, fn: b, options: c})
        },
        addAsyncTest: function (a) {
            p.push({name: null, fn: a})
        }
    }, r = function () {
    };
    r.prototype = q, r = new r;
    var s, t = [], u = b.documentElement, v = "svg" === u.nodeName.toLowerCase();
    !function () {
        var a = {}.hasOwnProperty;
        s = d(a, "undefined") || d(a.call, "undefined") ? function (a, b) {
            return b in a && d(a.constructor.prototype[b], "undefined")
        } : function (b, c) {
            return a.call(b, c)
        }
    }(), q._l = {}, q.on = function (a, b) {
        this._l[a] || (this._l[a] = []), this._l[a].push(b), r.hasOwnProperty(a) && setTimeout(function () {
            r._trigger(a, r[a])
        }, 0)
    }, q._trigger = function (a, b) {
        if (this._l[a]) {
            var c = this._l[a];
            setTimeout(function () {
                var a, d;
                for (a = 0; a < c.length; a++)(d = c[a])(b)
            }, 0), delete this._l[a]
        }
    }, r._q.push(function () {
        q.addTest = g
    }), v || !function (a, b) {
        function c(a, b) {
            var c = a.createElement("p"), d = a.getElementsByTagName("head")[0] || a.documentElement;
            return c.innerHTML = "x<style>" + b + "</style>", d.insertBefore(c.lastChild, d.firstChild)
        }

        function d() {
            var a = y.elements;
            return "string" == typeof a ? a.split(" ") : a
        }

        function e(a, b) {
            var c = y.elements;
            "string" != typeof c && (c = c.join(" ")), "string" != typeof a && (a = a.join(" ")), y.elements = c + " " + a, j(b)
        }

        function f(a) {
            var b = x[a[v]];
            return b || (b = {}, w++, a[v] = w, x[w] = b), b
        }

        function g(a, c, d) {
            if (c || (c = b), q)return c.createElement(a);
            d || (d = f(c));
            var e;
            return e = d.cache[a] ? d.cache[a].cloneNode() : u.test(a) ? (d.cache[a] = d.createElem(a)).cloneNode() : d.createElem(a), !e.canHaveChildren || t.test(a) || e.tagUrn ? e : d.frag.appendChild(e)
        }

        function h(a, c) {
            if (a || (a = b), q)return a.createDocumentFragment();
            c = c || f(a);
            for (var e = c.frag.cloneNode(), g = 0, h = d(), i = h.length; i > g; g++)e.createElement(h[g]);
            return e
        }

        function i(a, b) {
            b.cache || (b.cache = {}, b.createElem = a.createElement, b.createFrag = a.createDocumentFragment, b.frag = b.createFrag()), a.createElement = function (c) {
                return y.shivMethods ? g(c, a, b) : b.createElem(c)
            }, a.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + d().join().replace(/[\w\-:]+/g, function (a) {
                return b.createElem(a), b.frag.createElement(a), 'c("' + a + '")'
            }) + ");return n}")(y, b.frag)
        }

        function j(a) {
            a || (a = b);
            var d = f(a);
            return !y.shivCSS || p || d.hasCSS || (d.hasCSS = !!c(a, "article,aside,dialog,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}template{display:none}")), q || i(a, d), a
        }

        function k(a) {
            for (var b, c = a.getElementsByTagName("*"), e = c.length, f = RegExp("^(?:" + d().join("|") + ")$", "i"), g = []; e--;)b = c[e], f.test(b.nodeName) && g.push(b.applyElement(l(b)));
            return g
        }

        function l(a) {
            for (var b, c = a.attributes, d = c.length, e = a.ownerDocument.createElement(A + ":" + a.nodeName); d--;)b = c[d], b.specified && e.setAttribute(b.nodeName, b.nodeValue);
            return e.style.cssText = a.style.cssText, e
        }

        function m(a) {
            for (var b, c = a.split("{"), e = c.length, f = RegExp("(^|[\\s,>+~])(" + d().join("|") + ")(?=[[\\s,>+~#.:]|$)", "gi"), g = "$1" + A + "\\:$2"; e--;)b = c[e] = c[e].split("}"), b[b.length - 1] = b[b.length - 1].replace(f, g), c[e] = b.join("}");
            return c.join("{")
        }

        function n(a) {
            for (var b = a.length; b--;)a[b].removeNode()
        }

        function o(a) {
            function b() {
                clearTimeout(g._removeSheetTimer), d && d.removeNode(!0), d = null
            }

            var d, e, g = f(a), h = a.namespaces, i = a.parentWindow;
            return !B || a.printShived ? a : ("undefined" == typeof h[A] && h.add(A), i.attachEvent("onbeforeprint", function () {
                b();
                for (var f, g, h, i = a.styleSheets, j = [], l = i.length, n = Array(l); l--;)n[l] = i[l];
                for (; h = n.pop();)if (!h.disabled && z.test(h.media)) {
                    try {
                        f = h.imports, g = f.length
                    } catch (o) {
                        g = 0
                    }
                    for (l = 0; g > l; l++)n.push(f[l]);
                    try {
                        j.push(h.cssText)
                    } catch (o) {
                    }
                }
                j = m(j.reverse().join("")), e = k(a), d = c(a, j)
            }), i.attachEvent("onafterprint", function () {
                n(e), clearTimeout(g._removeSheetTimer), g._removeSheetTimer = setTimeout(b, 500)
            }), a.printShived = !0, a)
        }

        var p, q, r = "3.7.3", s = a.html5 || {}, t = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i, u = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i, v = "_html5shiv", w = 0, x = {};
        !function () {
            try {
                var a = b.createElement("a");
                a.innerHTML = "<xyz></xyz>", p = "hidden"in a, q = 1 == a.childNodes.length || function () {
                    b.createElement("a");
                    var a = b.createDocumentFragment();
                    return "undefined" == typeof a.cloneNode || "undefined" == typeof a.createDocumentFragment || "undefined" == typeof a.createElement
                }()
            } catch (c) {
                p = !0, q = !0
            }
        }();
        var y = {
            elements: s.elements || "abbr article aside audio bdi canvas data datalist details dialog figcaption figure footer header hgroup main mark meter nav output picture progress section summary template time video",
            version: r,
            shivCSS: s.shivCSS !== !1,
            supportsUnknownElements: q,
            shivMethods: s.shivMethods !== !1,
            type: "default",
            shivDocument: j,
            createElement: g,
            createDocumentFragment: h,
            addElements: e
        };
        a.html5 = y, j(b);
        var z = /^$|\b(?:all|print)\b/, A = "html5shiv", B = !q && function () {
                var c = b.documentElement;
                return !("undefined" == typeof b.namespaces || "undefined" == typeof b.parentWindow || "undefined" == typeof c.applyElement || "undefined" == typeof c.removeNode || "undefined" == typeof a.attachEvent)
            }();
        y.type += " print", y.shivPrint = o, o(b), "object" == typeof module && module.exports && (module.exports = y)
    }("undefined" != typeof a ? a : this, b);
    var w = {elem: i("modernizr")};
    r._q.push(function () {
        delete w.elem
    });
    var x = {style: w.elem.style};
    r._q.unshift(function () {
        delete x.style
    }), q.testProp = function (a, b, d) {
        return o([a], c, b, d)
    }, r.addTest("hidden", "hidden"in i("a")), r.addTest("webgl", function () {
        var b = i("canvas"), c = "probablySupportsContext"in b ? "probablySupportsContext" : "supportsContext";
        return c in b ? b[c]("webgl") || b[c]("experimental-webgl") : "WebGLRenderingContext"in a
    });
    var y = q._config.usePrefixes ? " -webkit- -moz- -o- -ms- ".split(" ") : [];
    q._prefixes = y;
    var z = q.testStyles = k;
    r.addTest("touchevents", function () {
        var c;
        if ("ontouchstart"in a || a.DocumentTouch && b instanceof DocumentTouch)c = !0; else {
            var d = ["@media (", y.join("touch-enabled),("), "heartz", ")", "{#modernizr{top:9px;position:absolute}}"].join("");
            z(d, function (a) {
                c = 9 === a.offsetTop
            })
        }
        return c
    }), e(), f(t), delete q.addTest, delete q.addAsyncTest;
    for (var A = 0; A < r._q.length; A++)r._q[A]();
    a.Modernizr = r
}(window, document);