/**
 * Author: Joseph Julian
 * Date: 10 June 2023
 * Class: CMSC 451
 */

package com.example.benchmark;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This class is responsible for generating a report from benchmark data.
 */
public class ReportGenerator {

    private static final int NUM_OF_DATA_SETS = 40;

    /**
     * This method generates a report from benchmark data selected by the user through JFileChooser.
     * The report is displayed in a JTable and contains one line for each data set size and five columns.
     * The first column contains the data set size, the second contains the average of the critical counts
     * for the 40 runs, and the third contains the coefficient of variance of those 40 values expressed as
     * a percentage. The fourth and fifth columns contain similar data for the times.
     */
    public static void generateReport() {
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                String line;
                List<String[]> data = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line);
                    int dataSetSize = Integer.parseInt(st.nextToken());
                    double[] counts = new double[NUM_OF_DATA_SETS];
                    double[] times = new double[NUM_OF_DATA_SETS];

                    for (int i = 0; i < NUM_OF_DATA_SETS; i++) {
                        counts[i] = Double.parseDouble(st.nextToken());
                        times[i] = Double.parseDouble(st.nextToken());
                    }

                    double avgCount = getMean(counts);
                    double avgTime = getMean(times);
                    double cvCount = getCoefficientOfVariance(counts, avgCount);
                    double cvTime = getCoefficientOfVariance(times, avgTime);

                    data.add(new String[]{
                            Integer.toString(dataSetSize),
                            Double.toString(avgCount),
                            Double.toString(cvCount),
                            Double.toString(avgTime),
                            Double.toString(cvTime)
                    });
                }

                // Display in JTable
                String[] columnNames = {"Data Set Size", "Average Count", "Coefficient of Variance Count (%)", "Average Time", "Coefficient of Variance Time (%)"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return String.class; // Set all columns to String type
                    }

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Disable editing of cells
                    }
                };

                for (String[] rowData : data) {
                    String[] formattedRowData = new String[rowData.length];
                    formattedRowData[0] = rowData[0]; // Data Set Size remains unchanged

                    // Format and set the remaining columns
                    for (int i = 1; i < rowData.length; i++) {
                        double value = Double.parseDouble(rowData[i]);
                        if (i % 2 == 0) {
                            // Columns 3 and 5: Coefficient of Variance (%)
                            formattedRowData[i] = formatNumber(value) + "%";
                        } else {
                            // Columns 2 and 4: Average
                            formattedRowData[i] = formatNumber(value);
                        }
                    }
                    model.addRow(formattedRowData);
                }

                JTable table = new JTable(model);
                customizeTableAppearance(table);

                JScrollPane scrollPane = new JScrollPane(table);

                JFrame frame = new JFrame("Benchmark Report");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

                // Resize the frame to fit the table
                table.setPreferredScrollableViewportSize(table.getPreferredSize());
                frame.pack();

                frame.setResizable(false);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method calculates the mean of an array of doubles.
     *
     * @param data An array of double values.
     * @return The mean of the data.
     */
    private static double getMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    /**
     * This method calculates the coefficient of variance for an array of doubles.
     *
     * @param data An array of double values.
     * @param mean The mean of the data.
     * @return The coefficient of variance of the data.
     */
    private static double getCoefficientOfVariance(double[] data, double mean) {
        double sum = 0;
        for (double value : data) {
            sum += Math.pow(value - mean, 2);
        }
        double standardDeviation = Math.sqrt(sum / data.length);
        return (standardDeviation / mean) * 100;
    }

    /**
     * Formats a number with the specified decimal format pattern.
     *
     * @param number The number to format.
     * @return The formatted number as a string.
     */
    private static String formatNumber(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(number);
    }

    /**
     * Customizes the appearance of the table.
     *
     * @param table The JTable to customize.
     */
    private static void customizeTableAppearance(JTable table) {
        // Set column widths
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(200);
        }

        // Center-align cell values
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Adjust table header font and alignment
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD));
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Main method to run the report generator standalone.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReportGenerator::generateReport);
    }
}
