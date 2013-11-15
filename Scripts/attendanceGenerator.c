#include<stdio.h>
#include<stdlib.h>

/* A simple script to create .csv files for an attendance list
 * Number of records to create specified in args
 * Returns output of the form - "studentID, attendance"
 *
 * USAGE  terminal compile 		-> gcc attendanceGenerator.c -o attgen
 * 	run with pipe out and arg	-> ./attgen 10 >record.csv
 * 	admire :)			-> libreoffice record.csv
 *
 * 	This will create 10 records in a file report.csv
 */

int random_number(int min_num, int max_num);


int main(int argc, char *argv[])
{
	int i;
	int no = atoi(argv[1]);
	printf("Student ID,Attendance\n");
	for (i=0; i<no; i++){
		
		int id = random_number(1111111,9999999); //these arguments set the range for each field
		int attendanceInt = random_number(0,3);
		char *attendance;
		if (attendanceInt == 1){
			attendance = "present";
		}else if(attendanceInt == 2){
			attendance = "absent";
		}else{
			attendance = "mv";
		}
		printf("%d,%s\n" ,id,attendance);	

	}
	return 1;
}

/* Pseudo random num generator
 * Uses system time in number genereation
 * Will return same/similar output if used multiple times in series
 */
int random_number(int min, int max)
{

	int divisor = RAND_MAX/(max+1);
    	int result;

	do { 
        	result = rand() / divisor;
    	} while (result > max || result < min);
        
        return result;
}
