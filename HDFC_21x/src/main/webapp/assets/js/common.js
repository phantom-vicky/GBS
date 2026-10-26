const CONDITIONS = {
        EQUAL: "=",
        NOT_EQUAL: "<>",
        GREATER_THAN: ">",
        LESSER_THAN: "<",
        GREATER_THAN_OR_EQUAL: ">=",
        LESSER_THAN_OR_EQUAL: "<=",
        LIKE: "LIKE"
    },
    COLUMN_SHORT_CODES = {
        CREATED_BY: "CR_BY",
        CREATED_DATE: "CR_DT",
        LAST_MOD_BY: "LM_BY",
        LAST_MOD_DATE: "LM_DT",
        VERIFIED_BY: "VR_BY",
        VERIFIED_DATE: "VR_DT",
        IS_DELETED: "IS_DL",
        VERIFIED_STATUS: "VR_ST",
        AUDIT_CRE_FLG: "AD_FG",
        IS_ACTIVE: "IS_ACT",
        ID: "ID"
    },
    SP = {
        INSERT: "SINGLE_TABLE_PLAIN_INSERT",
        MODIFY: "SINGLE_TABLE_PLAIN_MODIFY",
        VERIFY: "SINGLE_TABLE_PLAIN_VERIFY",
        DELETE: "SINGLE_TABLE_PLAIN_DELETE",
        ENQUIRY: "SINGLE_TABLE_SEL_INQUIRY"
    },
    CONJUNCTIONS = {
        AND: "AND",
        OR: "OR"
    },
    COMMON_YES_NO_METADATA = [{
        label: "Yes",
        value: "Y"
    }, {
        label: "No",
        value: "N"
    }],

        BANK_BRANCH_STATUS_METADATA = [{
            label: "ACTIVE",
            value: "1"
        }, {
            label: "MERGED",
            value: "2"
        },
        {
            label: "CLOSED",
            value: "3"
        },
        {
             label: "",
             value: "0"
        }
        ],
    COMMON_ENABLE_DISABLE_METADATA = [{
        label: "Enable",
        value: "0"
    }, {
        label: "Disable",
        value: "1"
    }],
    USER_LOGOUT_REASONS = {
        NORMAL_TYPE: "N",
        BROWSER_WINDOW_CLOSED: "WC",
        SESSION_EXPIRED: "SE",
        MIDDLEWARE_SHUTDOWN: "MS"
    },
    COMMON_BANK_STATUS = [{
                            label: "",
                            value: "0"
                           },
                           {
                           label: "Active",
                           value: "1"
                            },
                            {
                                 label: "Merged",
                                 value: "2"
                             },
                             {
                                label: "Closed",
                                value: "3"
                             }],
    OPERATION_MODE_SELECT = "S",
    OPERATION_MODE_SELECT_VERIFY = "J",
    OPERATION_MODE_SELECT_INQUIRY = "Q",
    OPERATION_MODE_INSERT = "I",
    OPERATION_MODE_MODIFY = "M",
    OPERATION_MODE_VERIFY = "V",
    OPERATION_MODE_DELETE = "D",
    CONSTANT_YES = "Y",
    CONSTANT_NO = "N",
    DATA_TABLE_OPTION = {
        dom: '<"top"<"col-md-8"l><"col-md-4"Bf>>rt<"bottom"ip><"clear">',
        lengthMenu: [
            [5, 10, 25, 50, -1],
            ["5", "10", "25", "50", "All"]
        ],
        buttons: [{
            extend: "collection",
            text: "Export",
            buttons: [{
                extend: "pdfHtml5",
                orientation: "landscape",
                pageSize: "LEGAL"
            }, "excel"]
        }]
    },
    configDataTableOptions = function(e) {
        var t = "Bank Name : HDFC\n Branch Name : " + e.solDesc + "\n Branch Id : " + e.solId,
            n = e.tableDesc ? e.tableDesc : "",
            o = e.tableNote ? e.tableNote : "",
            a = new Date,
            r = "Report Generated on : " + moment(a).format("DD/MM/YYYY HH:mm:ss");
        return {
            dom: '<"top"<"col-md-8"l><"col-md-4"Bf>>rt<"bottom"ip><"clear">',
            lengthMenu: [
                [5, 10, 25, 50, -1],
                ["5", "10", "25", "50", "All"]
            ],
            buttons: [{
                extend: "collection",
                text: "Export",
                buttons: [
 {
                        extend: 'pdfHtml5',
                        footer: true,
                        orientation: 'landscape',
                        pageSize: 'A2',
                        title: e.title,
                        messageTop: t + "\n" + n,
                        messageBottom: o + "\n" + r,
                        customize: function(doc) {
                            doc.pageMargins = [10, 10, 10,10];
//                           doc.content.splice( 0, 0, { margin: [ 0, 0, 0, 12 ], alignment: 'right', image: 'data:image/png;base64,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vDMW68HAU3ryqZXP3fbQ73/wL79650M22N80tQWFMNc0a5/+9Bc/dv3F5/zEW4KhcY9FLWk9DG367CTleUpMJJOCy6AgJ0ilZLnZvbj0w++8/tXXLDs9CTmngyvNZ25+4N//x08/duiohSiHu5kNFefve+CxUVYCAuF0htBmtQkJcumPP337l2/9tlX7cgpU0Xo5jIOmcbB2WBX3ZBz51J/e+u63fc+N153Hlna82DLGVryW9DAHsziWNdCIcIGuKM2HuQN3PfDQv/rVjy7NV+96yyuiSUBuF4gXajjSBCu0SyYGlPACW0esV0gD5poWHbFLsKhCnLvl9of//W997OM33fL4oQUOzqYF5jUfHU2jxy8+d/hD7/re97z1lddcfuEwyFMTDBaCg5LZNqsvGiZJenRQZiMLQph/2TVnX3wRgqmKDWIggvLxnERteyx3y8zY1ZjPzeWlxSZYJAdCkJV4WkkO2ziWqcDSxtzkaZLk8cUGT1JgzcibLcTVSYBdRmB7e1FXg6uvuOjiAwgEg1hBJYUtlzA83aCS/SDRYAF0suF4jIajM85areqK2DIRpYtNTGIUQRpKmarA0AotxIy5b9136E8/d3cVuqoFApC77rz3yYRYHmQqQJVUOdyZWipqGcuD6QGKQpQbzSARSWntzN2D97zlqpfsH0LIxMqoObKSb/36fb++7B/55NccEpelKstitTReW/zQR7/w/W+74dVXnZNzSVANwABy89oRTaIq6uhCvf6W73nRm1519vHugwPm4yZJ+NwX7v7Ep76UuBjqxXGTCUQmyyu7FtJP/8gNP/neGy44e6ma+drVL71o94L9wj//vZXxE40WJEqjYfTR0aOf+uRn3/GWV190zm54LhwhIRxLDEuUjaG1FINUsc2eJRCMNTxXIV7xknNeccWuydfWgMXd5370o1969LHD1TAAptSkDKw1l196xdzACBqtJCVITlOTMkL89ncO/+FH/uLwuoelRSHQqbR69tm7Lrn0JX/y57fTKpkBMQ733XPvQx/6yC3XXnbO8mJok6EkOGHWPS4ETMoAiMSyt3kAiTB01jY46/a7HvzlX/vY/NLiG2643OVyRtZU5W0mMwmjD5itK9ewdoW6wYfwReYllmxDWQYsLPz5l+796Kce+fgff3FtPKgX9ifMKzUBeb7OV1994Xvf+fL3veO15+6d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.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', width:90, height:40});

                        }
                    }, {
                    extend: "excel",
                    footer: !0,
                    title: e.title,
                    messageTop: t + "\n" + n,
                    messageBottom: o,
                    customizeData: function(e) {
                        for (var t = e.header.indexOf("S.No"), n = e.header.indexOf("Branch ID"), o = 0; o < e.body.length; o++) e.body[o][t] = "‌" + e.body[o][t], e.body[o][n] = "‌" + e.body[o][n]
                    }
                }, , {
                    extend: "csv",
                    footer: !0,
                    title: e.title,
                    customizeData: function(e) {
                        for (var t = e.header.indexOf("SR no"), n = e.header.indexOf("Branch ID"), o = 0; o < e.body.length; o++) e.body[o][t] = e.body[o][t], e.body[o][n] = e.body[o][n]
                    }
                }]
            }]
        }
    },
    configDataTableColSpanExcel = function(e) {
        var t = "Bank Name : XYZ BANK\n Branch Name : " + e.solDesc + "\n Branch Id : " + e.solId,
            n = e.tableDesc ? e.tableDesc : "",
            o = e.tableNote ? e.tableNote : "";
        return {
            dom: '<"top"<"col-md-8"l><"col-md-4"Bf>>rt<"bottom"ip><"clear">',
            ordering: !1,
            lengthMenu: [
                [5, 10, 25, 50, -1],
                ["5", "10", "25", "50", "All"]
            ],
            buttons: [{
                extend: "collection",
                text: "Export",
                buttons: [{
                    extend: "excel",
                    footer: !0,
                    title: e.title,
                    messageTop: t + "\n" + n,
                    messageBottom: o,
                    customize: function(e) {
                        var t = e.xl.worksheets["sheet1.xml"],
                            n = $("mergeCells", t);
                        n[0].appendChild(function(e, t, n) {
                            var o = e.createElement(t);
                            n && (n.attr && $(o).attr(n.attr), n.children && $.each(n.children, (function(e, t) {
                                o.appendChild(t)
                            })), null !== n.text && void 0 !== n.text && o.appendChild(e.createTextNode(n.text)));
                            return o
                        }(t, "mergeCell", {
                            attr: {
                                ref: "A1:E1"
                            }
                        })), n.attr("count", n.attr("count") + 1)
                    }
                }]
            }]
        }
    },
    configDataTableColSpan = function(e) {
        var t = "Bank Name :  XYZ BANK\n Branch Name : " + e.solDesc + "\n Branch Id : " + e.solId,
            n = e.tableDesc ? e.tableDesc : "",
            o = e.tableNote ? e.tableNote : "",
            a = new Date,
            r = "Report Generated on : " + moment(a).format("DD/MM/YYYY HH:mm:ss");
        return {
            dom: '<"top"<"col-md-8"l><"col-md-4"Bf>>rt<"bottom"ip><"clear">',
            ordering: !1,
            lengthMenu: [
                [5, 10, 25, 50, -1],
                ["5", "10", "25", "50", "All"]
            ],
            buttons: [{
                extend: "collection",
                text: "Export",
                buttons: [{
                    extend: "pdfHtml5",
                    footer: !0,
                    orientation: "landscape",
                    pageSize: "LEGAL",
                    title: e.title,
                    messageTop: t + "\n" + n,
                    messageBottom: o + "\n" + r,
                    customize: function(e) {
                        e.pageMargins = [10, 10, 10, 10], e.content[2].table.headerRows = 2;
                        var t = [];
                        $("#dataTable").find("thead>tr:first-child>th").each((function(e, n) {
                            var o = n.getAttribute("colSpan");
                            t.push({
                                text: n.innerHTML.replace(/<br>/g, ""),
                                style: "tableHeader",
                                colSpan: o
                            });
                            for (var a = 0; a < o - 1; a++) t.push({})
                        })), e.content[2].table.body.shift(), e.content[2].table.body.unshift(t)
                    }
                }, {
                    extend: "excel",
                    footer: !0,
                    title: e.title,
                    messageTop: t + "\n" + n,
                    messageBottom: o,
                    customize: function(e) {
                        var t = e.xl.worksheets["sheet1.xml"],
                            n = $("mergeCells", t);
                        n[0].appendChild(function(e, t, n) {
                            var o = e.createElement(t);
                            n && (n.attr && $(o).attr(n.attr), n.children && $.each(n.children, (function(e, t) {
                                o.appendChild(t)
                            })), null !== n.text && void 0 !== n.text && o.appendChild(e.createTextNode(n.text)));
                            return o
                        }(t, "mergeCell", {
                            attr: {
                                ref: "A1:E1"
                            }
                        })), n.attr("count", n.attr("count") + 1)
                    },
                    customizeData: function(e) {
                        for (var t = e.header.indexOf("Account Number"), n = 0; n < e.body.length; n++) e.body[n][t] = "‌" + e.body[n][t]
                    }
                }]
            }]
        }
    };

function disableZoom() {
    window.addEventListener("wheel", {
        passive: !1
    }), $(window).keydown((function(e) {
        "9" == e.which && $(this).next("input").focus(), 1 != e.ctrlKey || "107" != e.which && "109" != e.which || e.preventDefault(), $(window).bind("mousewheel DOMMouseScroll", (function(e) {
            1 == e.ctrlKey && e.preventDefault()
        }))
    }))
}

function isNumber(e) {
    var t = (e = e || window.event).which ? e.which : e.keyCode;
    return !(t > 31 && (t < 48 || t > 57) && 127 == t)
}

function isFloat(e) {
    var t = (e = e || window.event).which ? e.which : e.keyCode;
    return !(t > 31 && (t < 48 || t > 57) && 127 == t)
}

function saveTextAsFile(e, t) {
    if (e) {
        t || (t = "console.json");
        var n = new Blob([e], {
                type: "text/plain"
            }),
            o = document.createEvent("MouseEvents"),
            a = document.createElement("a");
        if (window.navigator && window.navigator.msSaveOrOpenBlob) window.navigator.msSaveOrOpenBlob(n, t);
        else {
            o = document.createEvent("MouseEvents");
            (a = document.createElement("a")).download = t, a.href = window.URL.createObjectURL(n), a.dataset.downloadurl = ["text/plain", a.download, a.href].join(":"), o.initEvent("click", !0, !1, window, 0, 0, 0, 0, 0, !1, !1, !1, !1, 0, null), a.dispatchEvent(o)
        }
    } else console.error("Console.save: No data")
}
disableZoom();
const saveByteArrayAsFile = function(e, t) {
        let n = new Blob([new Uint8Array(e)], {
            type: "application/octet-stream"
        });
        saveAs(n, t)
    },
    getBlobFromBase64String = function(e) {
        let t = window.atob(e);
        for (arrayBuffer = new ArrayBuffer(t.length), ia = new Uint8Array(arrayBuffer), i = 0; i < t.length; i++) ia[i] = t.charCodeAt(i);
        return new Blob([ia], {
            type: "application/octet-stream"
        })
    },
    convertToColumnValuesArray = e => {
        if ("object" == typeof e) {
            let t = [];
            for (let n of Object.keys(e)) e[n] && t.push({
                colName: n,
                value: e[n]
            });
            return t
        }
        throw "Only objects can be converted"
    },
    mapCommonMetaDataObject = e => {
        if ("object" == typeof e) {
            let t = {};
            return Object.keys(e).forEach((n => {
                t[n] = e[n]
            })), t
        }
        throw "Only objects can be mapped"
    },
    convertToDateObject = e => {
        if (e) {
            var t = e.split("/");
            return new Date(+t[2], t[1] - 1, +t[0])
        }
        return null
    },
    convertDateFormat = e => {
        var t = e.substr(3, 2),
            n = e.substr(0, 2),
            o = "20" + e.substr(6, 2) + "-" + t + "-" + n;
        return Date.parse(o)
    },
     differentiateOldValueNewValueBla = function() {
            setTimeout((() => {
                $(".old-new-value-table-bla .new-value").each(((e, t) => {
                    let n = $(t).text(),
                        o = $(t).parent().find(".old-value").text();
                    n && (n = n.trim()), o && (o = o.trim());
                     if(n == null || n === ""){
                         $(t).html("-");
                         return;
                     }
                    n == o ? $(t).html("-") : "-" != n && ($(t).addClass("text-danger"), $(t).css("font-weight", "600"))
                }))
            }), 1e3)
        };
    differentiateOldValueNewValue = function() {
        setTimeout((() => {
            $(".old-new-value-table .new-value").each(((e, t) => {
                let n = $(t).text(),
                    o = $(t).parent().find(".old-value").text();
                n && (n = n.trim()), o && (o = o.trim()), n == o ? $(t).html("-") : "-" != n && ($(t).addClass("text-danger"), $(t).css("font-weight", "600"))
            }))
        }), 1e3)
    };

function AvoidSpace(e) {
    if (32 == (e ? e.which : window.event.keyCode)) return !1
}

function onlyY(e) {
    var t = e ? e.which : window.event.keyCode;
    if (89 != t && 121 != t) return !1
}

function disableMinusEvent(e) {
    var t = e ? e.which : window.event.keyCode;
    if (95 == t || 45 == t) return !1
}
const handleErrorResponse = function(e) {
        /*var t = (e && e.data && e.data.message) ? e.data.message : "Server Error";
        if (e && e.status === 422) {
                Swal.fire("Warning", t, "warning");
            } else {
                Swal.fire("Error!", t, "error");
            }*/
        var t = e.data.message ? e.data.message : "Server Error";
        422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Error!", t, "error")
    },
    showSuccessAlert = function(e) {
        if (e) switch (e = e.toUpperCase()) {
            case "ADD":
                Swal.fire("Added!", "Data saved successfully", "success");
                break;
            case "MODIFY":
                Swal.fire("Modified!", "Data updated successfully", "success");
                break;
            case "VERIFY":
                Swal.fire("Verified!", "Data verified successfully", "success");
                break;
            case "DELETE":
                Swal.fire("Deleted!", "Data deleted successfully", "success")
        }
    },
    getDateWithoutTime = function(e) {
        return e ? moment(e).startOf("day").toDate() : null
    },
    dateConversion = function(e) {
        return e ? e.replace(/-/g, "/").replace(/T.+/, "") : null
    },
    getBlobFromBase64StringCAFU = function (e) {
        // Decode base64 string into a regular string (binary data)
        let t = window.atob(e);

        // Replace **all line breaks** (\n) with CRLF (\r\n) for Windows-style line endings
        t = t.replace(/\r?\n/g, '\r\n');  // Handles both LF (\n) and CRLF (\r\n), ensuring all line breaks are CRLF


        // Convert the string back into an array of bytes (UTF-8 encoding)
        let arrayBuffer = new ArrayBuffer(t.length);
        let ia = new Uint8Array(arrayBuffer);
        for (let i = 0; i < t.length; i++) {
            ia[i] = t.charCodeAt(i);
        }

        // Create and return the Blob with the correct CRLF endings
        return new Blob([ia], { type: "application/octet-stream" });
    };
