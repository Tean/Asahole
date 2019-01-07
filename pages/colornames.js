var colornames = {
    names: ['lavenderblush',
        'mediumpurple',
        'orangered',
        'gainsboro',
        'dimgray',
        'antiquewhite',
        'darkorange',
        'greenyellow',
        'burlywood',
        'bisque',
        'lightblue',
        'lightslategray',
        'white',
        'lightcyan',
        'springgreen',
        'brown',
        'rebeccapurple',
        'slategray',
        'darkgrey',
        'darkturquoise',
        'fuchsia',
        'orange',
        'mediumblue',
        'linen',
        'indianred',
        'darkred',
        'deepskyblue',
        'pink',
        'lightgreen',
        'yellow',
        'cornsilk',
        'navajowhite',
        'teal',
        'turquoise',
        'forestgreen',
        'seashell',
        'lawngreen',
        'darkorchid',
        'blue',
        'skyblue',
        'silver',
        'palevioletred',
        'cadetblue',
        'maroon',
        'mintcream',
        'darksalmon',
        'lavender',
        'darkkhaki',
        'darkgoldenrod',
        'mediumturquoise',
        'oldlace',
        'rosybrown',
        'gray',
        'beige',
        'darkslateblue',
        'darkslategrey',
        'lemonchiffon',
        'tan',
        'whitesmoke',
        'salmon',
        'wheat',
        'thistle',
        'darkmagenta',
        'lightseagreen',
        'grey',
        'lightskyblue',
        'violet',
        'aqua',
        'palegreen',
        'floralwhite',
        'palegoldenrod',
        'powderblue',
        'papayawhip',
        'crimson',
        'dimgrey',
        'darkviolet',
        'lightpink',
        'saddlebrown',
        'olivedrab',
        'lightyellow',
        'khaki',
        'black',
        'mediumseagreen',
        'moccasin',
        'mistyrose',
        'lightgray',
        'mediumvioletred',
        'dodgerblue',
        'lightgrey',
        'darkcyan',
        'darkgray',
        'yellowgreen',
        'mediumaquamarine',
        'magenta',
        'limegreen',
        'slateblue',
        'seagreen',
        'sienna',
        'aliceblue',
        'steelblue',
        'mediumslateblue',
        'green',
        'hotpink',
        'blueviolet',
        'paleturquoise',
        'blanchedalmond',
        'lightcoral',
        'lightsteelblue',
        'darkseagreen',
        'deeppink',
        'chartreuse',
        'plum',
        'honeydew',
        'lightgoldenrodyellow',
        'lightsalmon',
        'mediumorchid',
        'gold',
        'olive',
        'ivory',
        'goldenrod',
        'darkslategray',
        'slategrey',
        'purple',
        'lightslategrey',
        'chocolate',
        'orchid',
        'lime',
        'coral',
        'mediumspringgreen',
        'darkgreen',
        'aquamarine',
        'darkblue',
        'peru',
        'cornflowerblue',
        'tomato',
        'ghostwhite',
        'sandybrown',
        'peachpuff',
        'azure',
        'navy',
        'indigo',
        'snow',
        'cyan',
        'firebrick',
        'midnightblue',
        'royalblue',
        'red',
        'darkolivegreen']
};
colornames.getRandomColorName = count => {
    if (count <= 0) return [];
    var cps = [];
    colornames.names.forEach(n => {
        cps.push(n);
    });
    var colors = [];
    for (var i = 0; i < count; i++) {
        if (cps.length > 0) {
            const index = parseInt(Math.random() * cps.length);
            colors.push(cps[index]);
            cps.splice(index, 1);
        }
    }
    return colors;
}

function RpeatColor(r, g, b, onran, step) {
    this.r = r == null ? 0 : typeof (r) === 'number' ? parseInt(r) : 0;
    this.g = g == null ? 0 : typeof (g) === 'number' ? parseInt(g) : 0;
    this.b = b == null ? 0 : typeof (b) === 'number' ? parseInt(b) : 0;
    this.onran = onran == null ? true : typeof (onran) === 'boolean' ? onran : true;
    this.step = step == null ? 5 : typeof (step) === 'number' ? parseInt(step) : 1;
    this.rmin = 10;
    this.rmax = 245;
    this.gmin = 10;
    this.gmax = 245;
    this.bmin = 10;
    this.bmax = 245;
    this.rdir = true;
    this.gdir = true;
    this.bdir = true;
}
RpeatColor.prototype.randomNext = function () {
    if (this.onran) {
        // if (this.ranFlex == null) {
        const c = parseInt(Math.random() * 7);
        this.ranFlex = c;
        // }
        this.onran = false;
    }
    if (this.ranFlex != null) {
        switch (this.ranFlex) {
            case 0:
                if (this.rdir) {
                    this.r = this.r + this.step;
                } else {
                    this.r = this.r - this.step;
                }
                if (this.r > this.rmax || this.r < this.rmin) {
                    this.rdir = !this.rdir;
                    if (this.r < this.rmin) this.r = this.rmin;
                    if (this.r > this.rmax) this.r = this.rmax;
                    this.onran = true;
                }
                break;
            case 1:
                if (this.gdir) {
                    this.g = this.g + this.step;
                } else {
                    this.g = this.g - this.step;
                }
                if (this.g > this.gmax || this.g < this.gmin) {
                    this.gdir = !this.gdir;
                    if (this.g < this.gmin) this.g = this.gmin;
                    if (this.g > this.gmax) this.g = this.gmax;
                    this.onran = true;
                }
                break;
            case 2:
                if (this.bdir) {
                    this.b = this.b + this.step;
                } else {
                    this.b = this.b - this.step;
                }
                if (this.b > this.bmax || this.b < this.bmin) {
                    this.bdir = !this.bdir;
                    if (this.b < this.bmin) this.b = this.bmin;
                    if (this.b > this.bmax) this.b = this.bmax;
                    this.onran = true;
                }
                break;


            case 3:
                if (this.rdir) {
                    this.r = this.r + this.step;
                } else {
                    this.r = this.r - this.step;
                }
                if (this.r > this.rmax || this.r < this.rmin) {
                    this.rdir = !this.rdir;
                    if (this.r < this.rmin) this.r = this.rmin;
                    if (this.r > this.rmax) this.r = this.rmax;
                    this.onran = true;
                }

                if (this.gdir) {
                    this.g = this.g + this.step;
                } else {
                    this.g = this.g - this.step;
                }
                if (this.g > this.gmax || this.g < this.gmin) {
                    this.gdir = !this.gdir;
                    if (this.g < this.gmin) this.g = this.gmin;
                    if (this.g > this.gmax) this.g = this.gmax;
                    this.onran = true;
                }
                break;


            case 4:
                if (this.rdir) {
                    this.r = this.r + this.step;
                } else {
                    this.r = this.r - this.step;
                }
                if (this.r > this.rmax || this.r < this.rmin) {
                    this.rdir = !this.rdir;
                    if (this.r < this.rmin) this.r = this.rmin;
                    if (this.r > this.rmax) this.r = this.rmax;
                    this.onran = true;
                }

                if (this.bdir) {
                    this.b = this.b + this.step;
                } else {
                    this.b = this.b - this.step;
                }
                if (this.b > this.bmax || this.b < this.bmin) {
                    this.bdir = !this.bdir;
                    if (this.b < this.bmin) this.b = this.bmin;
                    if (this.b > this.bmax) this.b = this.bmax;
                    this.onran = true;
                }
                break;


            case 5:
                if (this.gdir) {
                    this.g = this.g + this.step;
                } else {
                    this.g = this.g - this.step;
                }
                if (this.g > this.gmax || this.g < this.gmin) {
                    this.gdir = !this.gdir;
                    if (this.g < this.gmin) this.g = this.gmin;
                    if (this.g > this.gmax) this.g = this.gmax;
                    this.onran = true;
                }

                if (this.bdir) {
                    this.b = this.b + this.step;
                } else {
                    this.b = this.b - this.step;
                }
                if (this.b > this.bmax || this.b < this.bmin) {
                    this.bdir = !this.bdir;
                    if (this.b < this.bmin) this.b = this.bmin;
                    if (this.b > this.bmax) this.b = this.bmax;
                    this.onran = true;
                }
                break;


            case 6:
                if (this.rdir) {
                    this.r = this.r + this.step;
                } else {
                    this.r = this.r - this.step;
                }
                if (this.r > this.rmax || this.r < this.rmin) {
                    this.rdir = !this.rdir;
                    if (this.r < this.rmin) this.r = this.rmin;
                    if (this.r > this.rmax) this.r = this.rmax;
                    this.onran = true;
                }

                if (this.gdir) {
                    this.g = this.g + this.step;
                } else {
                    this.g = this.g - this.step;
                }
                if (this.g > this.gmax || this.g < this.gmin) {
                    this.gdir = !this.gdir;
                    if (this.g < this.gmin) this.g = this.gmin;
                    if (this.g > this.gmax) this.g = this.gmax;
                    this.onran = true;
                }

                if (this.bdir) {
                    this.b = this.b + this.step;
                } else {
                    this.b = this.b - this.step;
                }
                if (this.b > this.bmax || this.b < this.bmin) {
                    this.bdir = !this.bdir;
                    if (this.b < this.bmin) this.b = this.bmin;
                    if (this.b > this.bmax) this.b = this.bmax;
                    this.onran = true;
                }
                break;
        }
    }
}
RpeatColor.prototype.rpeatStart = function () {
    if (this.rpId == null || this.rpId < 0) {
        const rc = this;
        console.log('rpstart');
        this.rpId = setInterval(() => {
            rc.randomNext();
        }, 20);
    }
}
RpeatColor.prototype.rpeatStop = function () {
    clearInterval(this.rpId);
    this.rpId = -1;
}