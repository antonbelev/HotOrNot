#include<stdio.h>
#include<stdlib.h>

/* A simple script to create .csv files for a timestamp database
 * Takes a venue ID from args 
 * Returns output of the form - "hh:mm:ss,YYYY/MM/DD,venueID"
 */

int random_number(int min_num, int max_num);


int main(int argc, char *argv[])
{
	int i;
	int year = 2013; //year set to 2013
	int month;
	int day;
	int hour;
	int minute;
	int second;
	char *venue = argv[1];
	for (i=0; i<100; i++){
		
		month = random_number(1,12); //these arguments set the range for each field
		day = random_number(1,31);
		hour = random_number(0,23);
		minute = random_number(0,59);
		second = random_number(0,59);
		printf("%d:%d:%d,%d/%d/%d,%s\n" ,hour,minute,second,year,month,day,venue);	

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
