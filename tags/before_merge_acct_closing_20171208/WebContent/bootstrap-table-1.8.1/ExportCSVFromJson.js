
(function (window) {
    "use strict";
    /**
     Default constructor
     */

    var _TXT = function (JSONData, fileName, txtHeader, columnName) {


        if (typeof JSONData === 'undefined') {
            alert('json data is null');
            return;
        }
        var txtData = JSONData,
                txtColumn,
                txtEncoding = "data:text/txt;charset=utf-8,%EF%BB%BF",
                txtOutput = "",
                txtRows = [],
                BREAK = '\r\n',
                OTHER_DELIMITER = '"',
                DELIMITER = '|',
                FILENAME = fileName + ".txt";


        txtColumn = columnName;
        txtOutput += txtHeader.join('|') + BREAK;


        console.log(" _TXT : ");
        for (var i = 0; i < txtData.length; i++) {
            var rowElements = [];
            for (var k = 0; k < txtColumn.length; k++) {

                rowElements.push(OTHER_DELIMITER + (txtData[i][txtColumn[k]]) + OTHER_DELIMITER);

            } // Write the row array based on the headers

            txtRows.push(rowElements.join(DELIMITER));
        }

        txtOutput += txtRows.join(BREAK);
        var a = document.createElement("a");

        if (navigator.msSaveBlob) { // IE10
            navigator.msSaveBlob(new Blob([txtOutput], {type: "text/txt"}), FILENAME);
        } else if ('download' in a) { //html5 A[download]
            a.href = txtEncoding + encodeURIComponent(txtOutput);
            a.download = FILENAME;
            document.body.appendChild(a);
            setTimeout(function () {
                a.click();
                document.body.removeChild(a);
            }, 66);
        } else if (document.execCommand) { // Other version of IE
            var oWin = window.open("about:blank", "_blank");
            oWin.document.write(txtOutput);
            oWin.document.close();
            oWin.document.execCommand('SaveAs', true, FILENAME);
            oWin.close();
        } else {
            alert("Support for your specific browser hasn't been created yet, please check back later.");
        }
    };
    
        var _CSV = function (JSONData, fileName, csvHeader, columnName) {


        if (typeof JSONData === 'undefined') {
            alert('json data is null');
            return;
        }
        var csvData = JSONData,
                csvColumn,
                csvEncoding = "data:text/csv;charset=utf-8,%EF%BB%BF",
                csvOutput = "",
                csvRows = [],
                BREAK = '\r\n',
                DELIMITER = ',',
                FILENAME = fileName + ".csv";

        csvColumn = columnName;
        csvOutput += csvHeader.join(',') + BREAK;


        console.log(" _CSV : ");
        for (var i = 0; i < csvData.length; i++) {
            var rowElements = [];
            for (var k = 0; k < csvColumn.length; k++) {
                rowElements.push(csvData[i][csvColumn[k]]);


            } // Write the row array based on the headers
            csvRows.push(rowElements.join(DELIMITER));
        }

        csvOutput += csvRows.join(BREAK);
        var a = document.createElement("a");

        if (navigator.msSaveBlob) { // IE10
            navigator.msSaveBlob(new Blob([csvOutput], {type: "text/csv"}), FILENAME);
        } else if ('download' in a) { //html5 A[download]
            a.href = csvEncoding + encodeURIComponent(csvOutput);
            a.download = FILENAME;
            document.body.appendChild(a);
            setTimeout(function () {
                a.click();
                document.body.removeChild(a);
            }, 66);
        } else if (document.execCommand) { // Other version of IE
            var oWin = window.open("about:blank", "_blank");
            oWin.document.write(csvOutput);
            oWin.document.close();
            oWin.document.execCommand('SaveAs', true, FILENAME);
            oWin.close();
        } else {
            alert("Support for your specific browser hasn't been created yet, please check back later.");
        }
    };
    

    var _Excel = function (JSONData, fileName, sheetName, excelHeader, columnName) {

        console.log(" _Excel4 : ");
        JSONData = JSON.stringify(JSONData);
        var tableData = '<tbody><tr>';
        excelHeader.forEach(function (item, index, arr) {
            tableData += '            <th>' + item + '</th>';
        });
        tableData += '        </tr>';

        var result = $.parseJSON(JSONData);
        $.each(result, function (k, v) {
            console.log(" k : " + k);
            console.log(" v : " + v);
            var indexJson = 0;
            $.each(v, function (kk, vv) {
                if (columnName.indexOf(kk) != -1) {
                    console.log(" kk : " + kk);
                    console.log(" vv : " + vv);
                    if (indexJson == 0) {
                        tableData += '        <tr>';
                    }
                    tableData += '<td>' + vv + '</td>';
                    indexJson++;
                }
            });

            tableData += '        </tr>';
        });
        tableData += '    </tbody>';

        var uri = 'data:application/vnd.ms-excel;base64,',
                template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>',
                base64 = function (s) {
                    return window.btoa(unescape(encodeURIComponent(s)))
                },
                format = function (s, c) {
                    return s.replace(/{(\w+)}/g, function (m, p) {
                        return c[p];
                    })
                },
                FILENAME = fileName + ".xls",
                ctx = {worksheet: sheetName, table: tableData};


        var a = document.createElement("a");
        if (navigator.msSaveBlob) { // IE10
            navigator.msSaveBlob(new Blob([format(template, ctx)], {type: "application/vnd.ms-excel;"}), FILENAME);
        } else if ('download' in a) { //html5 A[download]
            a.href = uri + base64(format(template, ctx));
            a.download = FILENAME;
            document.body.appendChild(a);
            setTimeout(function () {
                a.click();
                document.body.removeChild(a);
            }, 66);

        } else if (document.execCommand) { // Other version of IE
            var oWin = window.open("about:blank", "_blank");
            oWin.document.write(format(template, ctx));
            oWin.document.close();
            oWin.document.execCommand('SaveAs', true, FILENAME);
            oWin.close();
        } else {
            alert("Support for your specific browser hasn't been created yet, please check back later.");
        }

    };

    window.FuncJsonToTxt = _TXT;
    window.FuncJsonToCsv = _CSV;
    window.FuncJsonToExcel = _Excel;

})(window);

if (!Object.keys) {
    Object.keys = (function () {
        'use strict';
        var hasOwnProperty = Object.prototype.hasOwnProperty,
                hasDontEnumBug = !({toString: null}).propertyIsEnumerable('toString'),
                dontEnums = [
                    'toString',
                    'toLocaleString',
                    'valueOf',
                    'hasOwnProperty',
                    'isPrototypeOf',
                    'propertyIsEnumerable',
                    'constructor'
                ],
                dontEnumsLength = dontEnums.length;

        return function (obj) {
            if (typeof obj !== 'object' && (typeof obj !== 'function' || obj === null)) {
                throw new TypeError('Object.keys called on non-object');
            }

            var result = [], prop, i;

            for (prop in obj) {
                if (hasOwnProperty.call(obj, prop)) {
                    result.push(prop);
                }
            }

            if (hasDontEnumBug) {
                for (i = 0; i < dontEnumsLength; i++) {
                    if (hasOwnProperty.call(obj, dontEnums[i])) {
                        result.push(dontEnums[i]);
                    }
                }
            }
            return result;
        };
    }());
}