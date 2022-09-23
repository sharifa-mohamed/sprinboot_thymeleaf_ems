 function drawBarChart(divId, xData, yData, xTitle, yTitle){

 new ApexCharts(document.querySelector(divId), {
                    series: [{
                      data:  yData
                    }],
                    chart: {
                      type: 'bar',
                      height: 450,
                      width:400
                    },
                    plotOptions: {
                      bar: {
                        borderRadius: 4,
                        horizontal: false,
                      }
                    },
                    dataLabels: {
                      enabled: true
                    },
                    xaxis: {
                      categories: xData ,
                       title: {
                          text: xTitle,
                          offsetX: 0,
                          offsetY: 0,
                          style: {
                              color: undefined,
                              fontSize: '12px',
                              fontFamily: 'Helvetica, Arial, sans-serif',
                              fontWeight: 600,
                              cssClass: 'apexcharts-xaxis-title',
                          },
                         }

                    },
                    yaxis: {
                     title: {
                       text: yTitle,
                          rotate: -90,
                          offsetX: 0,
                          offsetY: 0,
                          style: {
                              color: undefined,
                              fontSize: '12px',
                              fontFamily: 'Helvetica, Arial, sans-serif',
                              fontWeight: 600,
                              cssClass: 'apexcharts-yaxis-title',
                             },
                                } }

                  }).render();
                        }

